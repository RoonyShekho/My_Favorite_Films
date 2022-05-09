package com.example.myfavoritefilms.presentation.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class DetailsViewModel @Inject constructor(
    val useCase:UseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie = _selectedMovie


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val movieId = savedStateHandle.get<Int>("movieId")
            _selectedMovie.value = movieId?.let {
                useCase.getSelectedMovie(it)
            }
        }
    }
}