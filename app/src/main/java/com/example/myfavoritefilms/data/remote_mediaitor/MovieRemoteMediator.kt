package com.example.myfavoritefilms.data.remote_mediaitor

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.domain.model.MovieRemoteKey
import com.example.myfavoritefilms.data.local.database.MovieDatabase
import com.example.myfavoritefilms.data.remote.MovieApi
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRemoteMediator @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, Movie>() {

    private val movieDao = movieDatabase.movieDao
    private val movieRemoteKeysDao = movieDatabase.movieRemoteKeyDao

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = movieRemoteKeysDao.getRemoteKey( 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440 // 24 hours

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) InitializeAction.SKIP_INITIAL_REFRESH
        else InitializeAction.LAUNCH_INITIAL_REFRESH
    }



    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Movie>
    ): MovieRemoteKey? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                movieRemoteKeysDao.getRemoteKey(id)
            }
        }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Movie>
    ): MovieRemoteKey? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero -> movieRemoteKeysDao.getRemoteKey(hero.id) }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Movie>
    ): MovieRemoteKey? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero -> movieRemoteKeysDao.getRemoteKey(hero.id) }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = movieApi.getAllMovies(page)

            if (response.movies.isNotEmpty()) {
                movieDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        movieDao.deleteAllMovies()
                        movieRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.movies.map { film ->
                        MovieRemoteKey(
                            id = film.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    movieRemoteKeysDao.addAllRemoteKeys(keys)
                    movieDao.addMovies(response.movies)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            Log.e("HeroRemoteMediator", e.toString())
            return MediatorResult.Error(e)
        }
    }
}