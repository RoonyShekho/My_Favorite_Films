package com.example.myfavoritefilms.presentation.screen.splash


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted:StateFlow<Boolean> = _onBoardingCompleted


    init {
        viewModelScope.launch (Dispatchers.IO){
            _onBoardingCompleted.value =
            useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }

}