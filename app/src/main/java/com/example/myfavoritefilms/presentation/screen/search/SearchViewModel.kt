package com.example.myfavoritefilms.presentation.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val searchMovies = _searchMovies

    fun searchMovies(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchMoviesUseCase(query).cachedIn(viewModelScope).collect {
                _searchMovies.value = it
            }
        }
    }

    fun updateQuery(query:String){
        _searchQuery.value = query
    }

}