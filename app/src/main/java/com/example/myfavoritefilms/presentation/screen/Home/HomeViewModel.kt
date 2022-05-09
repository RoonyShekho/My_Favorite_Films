package com.example.myfavoritefilms.presentation.screen.Home


import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel() {
    val getAllMovie = useCases.getAllMoviesUseCase()
}