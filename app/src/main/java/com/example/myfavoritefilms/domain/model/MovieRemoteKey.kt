package com.example.myfavoritefilms.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "remote_key_table"
)
data class MovieRemoteKey(
    @PrimaryKey
    val id:Int,
    val prevPage:Int?,
    val nextPage:Int?,
    val lastUpdated:Long?
)