package com.example.myfavoritefilms.domain.useCases

import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.data.repository.Repository
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class ReadOnBoardingUseCase(
    private val repository: Repository
) {

    operator fun invoke():Flow<Boolean>{
        return repository.readOnBoardingStates()
    }

}