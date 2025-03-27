package com.mitroshenko.newjob.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM my_table")
    fun getAlphabetizedProducts(): Flow<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(prod: Entity)

    @Delete
    suspend fun delete(prod: Entity)

    @Query("DELETE FROM my_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(prod: Entity)
}