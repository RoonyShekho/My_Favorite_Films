package com.example.myfavoritefilms.domain.useCases

import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.data.repository.Repository
import com.example.myfavoritefilms.domain.model.Movie

@ExperimentalPagingApi
class GetSelectedMovie(
    private val repository: Repository
) {

    suspend operator fun invoke(movieId:Int): Movie {
        return repository.getSelectedMovie(movieId)
    }
}