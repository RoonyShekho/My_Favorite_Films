package com.example.myfavoritefilms.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "movie_table"
)

data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val plot: String,
    val rating: Double,
    val title: String
)