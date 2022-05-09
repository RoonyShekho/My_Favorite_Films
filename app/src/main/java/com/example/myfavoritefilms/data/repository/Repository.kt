package com.example.myfavoritefilms.data.repository

import androidx.compose.runtime.compositionLocalOf
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.myfavoritefilms.data.local.LocalDataSource

import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.data.local.RemoteDataSource
import com.example.myfavoritefilms.data.remote.MovieApi
import com.example.myfavoritefilms.domain.repository.DatastoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class Repository @Inject constructor(
    private val datastore: DatastoreOperations,
    private val movieApi: MovieApi,
    val remote: RemoteDataSource,
    private val local:LocalDataSource
) {


    suspend fun getSelectedMovie(movieId:Int):Movie =
        local.getSelectedMovie(movieId)


     fun getAllMovies():Flow<PagingData<Movie>> =
        remote.getAllHeroes()

   fun searchMovie(query:String):Flow<PagingData<Movie>>{
        return remote.searchMovies(query)
    }

    suspend fun saveOnBoardingState(completed:Boolean){
        datastore.saveOnBoardingState(completed)
    }


    fun readOnBoardingStates():Flow<Boolean>{
        return datastore.readOnBoardingState()
    }

}