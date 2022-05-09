package com.example.myfavoritefilms.domain.useCases

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.data.repository.Repository
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class SearchMoviesUseCase (
    private val repository: Repository
        ){

   operator fun invoke(query:String):Flow<PagingData<Movie>>{
        return repository.searchMovie(query)
    }

}