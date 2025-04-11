package com.mitroshenko.newjob.data.repository.basket

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Query("SELECT * FROM my_table")
    fun getAlphabetizedProducts(): Flow<List<BasketEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(prod: BasketEntity)

    @Delete
    suspend fun delete(prod: BasketEntity)

    @Query("DELETE FROM my_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(prod: BasketEntity)
}