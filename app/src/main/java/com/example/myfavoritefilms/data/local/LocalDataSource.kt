package com.example.myfavoritefilms.data.local

import com.example.myfavoritefilms.data.local.database.MovieDatabase
import com.example.myfavoritefilms.domain.model.Movie
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    movieDatabase: MovieDatabase
) {

    private val dao = movieDatabase.movieDao

    suspend fun getSelectedMovie(movieId:Int): Movie =
        dao.getSelectedMovie(movieId)
}