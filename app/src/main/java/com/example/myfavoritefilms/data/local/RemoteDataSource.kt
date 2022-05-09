package com.example.myfavoritefilms.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.data.local.database.MovieDatabase
import com.example.myfavoritefilms.data.pagingSource.SearchMoviesSourceFactory
import com.example.myfavoritefilms.data.remote.MovieApi
import com.example.myfavoritefilms.data.remote_mediaitor.MovieRemoteMediator
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSource(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) {

    private val heroDao = movieDatabase.movieDao

     fun getAllHeroes(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { heroDao.getAllMovies() }
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = MovieRemoteMediator(
                 movieApi,
                 movieDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

     fun searchMovies(query: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = {
                SearchMoviesSourceFactory(movieApi = movieApi, query = query)
            }
        ).flow
}