package com.example.myfavoritefilms.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myfavoritefilms.domain.model.MovieRemoteKey
import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.data.local.DatabaseConverter
import com.example.myfavoritefilms.data.local.dao.MovieDao
import com.example.myfavoritefilms.data.local.dao.RemoteKeyDao


@Database(
    entities = [Movie::class, MovieRemoteKey::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class MovieDatabase:RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val movieRemoteKeyDao: RemoteKeyDao
}