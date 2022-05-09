package com.example.myfavoritefilms.di

import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.data.local.RemoteDataSource
import com.example.myfavoritefilms.data.local.database.MovieDatabase
import com.example.myfavoritefilms.util.Constants.BASE_URL
import com.example.myfavoritefilms.data.remote.MovieApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private val json: Json = Json {
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(contentType),
            )
            .build()
    }


   @Provides
    @Singleton
    fun provideBorutoApi(
        retrofit:Retrofit
    ): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }


   @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: MovieApi,
        borutoDatabase: MovieDatabase
    ): RemoteDataSource {
        return RemoteDataSource(
            borutoApi,
            borutoDatabase
        )
    }

}