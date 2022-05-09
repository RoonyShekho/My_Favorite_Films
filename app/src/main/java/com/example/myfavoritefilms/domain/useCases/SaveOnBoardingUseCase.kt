package com.example.myfavoritefilms.domain.useCases

import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.data.repository.Repository

@ExperimentalPagingApi
class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed:Boolean){
        repository.saveOnBoardingState(completed)
    }
}