package com.example.myfavoritefilms.data.remote

import com.example.myfavoritefilms.domain.model.ApiResponse
import com.example.myfavoritefilms.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/movies")
    suspend fun getAllMovies(
       @Query("page") page:Int = 1
    ): ApiResponse


    @GET("/film/search")
    suspend fun searchMovies(
        @Query("title") title:String
    ): ApiResponse




}