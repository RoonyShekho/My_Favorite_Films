package com.example.myfavoritefilms.domain.useCases

import androidx.paging.ExperimentalPagingApi

@ExperimentalPagingApi
data class UseCases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val getAllMoviesUseCase: GetAllMoviesUseCase,
    val searchMoviesUseCase: SearchMoviesUseCase,
    val getSelectedMovie: GetSelectedMovie
)
