package com.example.myfavoritefilms.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class WelcomeViewModel
@Inject constructor(
    private val useCases: UseCases
):ViewModel() {

    fun saveOnBoardingState(completed:Boolean){
        viewModelScope.launch(Dispatchers.IO){
            useCases.saveOnBoardingUseCase(completed = completed)
        }
    }

}