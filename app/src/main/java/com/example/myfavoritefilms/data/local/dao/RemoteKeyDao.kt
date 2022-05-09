package com.example.myfavoritefilms.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfavoritefilms.domain.model.MovieRemoteKey

@Dao
interface RemoteKeyDao {

    @Query("SELECT * FROM remote_key_table WHERE id=:id")
    suspend fun getRemoteKey(id:Int): MovieRemoteKey?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys:List<MovieRemoteKey>)


    @Query("DELETE FROM remote_key_table")
    suspend fun deleteAllRemoteKeys()
}