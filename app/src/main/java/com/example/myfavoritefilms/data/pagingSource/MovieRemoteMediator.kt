package com.example.myfavoritefilms.data.pagingSource

/*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.myfavoritefilms.data.model.Movie
import com.example.myfavoritefilms.data.model.MovieRemoteKey
import com.example.myfavoritefilms.data.model.local.database.MovieDatabase
import com.example.myfavoritefilms.data.remote.MovieApi
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRemoteMediator @Inject constructor (
    private val movieApi: MovieApi,
    private val movieDatabase:MovieDatabase
    ):RemoteMediator<Int, Movie>() {

    private val movieDao = movieDatabase.movieDao()
    private val movieRemoteKeyDao = movieDatabase.remoteKeyDao()


   override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = movieRemoteKeyDao.getRemoteKey(1)?.lastUpdated ?: 0L
        val cacheTimeout = 5

        val diffInTime = (currentTime - lastUpdated) / 1000/60

        return if(diffInTime.toInt() <= cacheTimeout){
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {

        return try {
            val page = when(loadType){
                LoadType.REFRESH->{
                    val remoteKeys= getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1)?:1
                }

                LoadType.PREPEND->{
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: MediatorResult.Success(
                            endOfPaginationReached = remoteKeys!=null
                        )

                    prevPage
                }

                LoadType.APPEND->{
                    val remoteKeys = getRemoteKeyForLastItem(state)

                    val nextPage = remoteKeys?.nextPage
                        ?:MediatorResult.Success(
                            endOfPaginationReached = remoteKeys!=null
                        )

                    nextPage
                }
            }

            val response = movieApi.getAllMovies(page = page as Int)

            if (response.films.isNotEmpty()) {
                movieDatabase.withTransaction {

                    if (loadType == LoadType.REFRESH) {
                        movieDao.deleteAllMovies()
                        movieRemoteKeyDao.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage

                    val keys = response.films.map { movie ->
                        MovieRemoteKey(
                            id = movie.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    movieDao.addMovies(response.films)
                    movieRemoteKeyDao.addAllRemoteKeys(keys)
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        }catch(e:Exception){
            MediatorResult.Error(e)
        }

    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Movie>):MovieRemoteKey?  {
        return state.pages.lastOrNull{
            it.data.isNotEmpty()
        }?.data?.lastOrNull()
            ?.let { hero->
                movieRemoteKeyDao.getRemoteKey(hero.id)
            }
    }

    private suspend fun  getRemoteKeyForFirstItem(state: PagingState<Int, Movie>): MovieRemoteKey? {
        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {movie->
            movieRemoteKeyDao.getRemoteKey(movie.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Movie>): MovieRemoteKey? {
        return state.anchorPosition?.let {position->
            state.closestItemToPosition(position)?.id?.let {id->
                movieRemoteKeyDao.getRemoteKey(id)
            }
        }
    }
}*/
