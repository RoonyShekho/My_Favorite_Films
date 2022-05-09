package com.example.myfavoritefilms.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.data.repository.DatastoreOperationsImp
import com.example.myfavoritefilms.data.repository.Repository
import com.example.myfavoritefilms.domain.repository.DatastoreOperations
import com.example.myfavoritefilms.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext
        context:Context
    ): DatastoreOperations {
        return DatastoreOperationsImp(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository):UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllMoviesUseCase = GetAllMoviesUseCase(repository),
            searchMoviesUseCase = SearchMoviesUseCase(repository),
            getSelectedMovie = GetSelectedMovie(repository)
        )
    }


}