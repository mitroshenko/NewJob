package com.mitroshenko.newjob.data.repository.basket

import android.content.Context
import androidx.annotation.WorkerThread
import com.mitroshenko.newjob.data.database.BasketRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class BasketRepository(private val basketDao: BasketDao) {
    val allProducts: Flow<List<BasketEntity>> = basketDao.getAlphabetizedProducts()

    @WorkerThread
    suspend fun insert(basketEntity: BasketEntity) {
        basketDao.insert(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        basketDao.delete(basketEntity)
    }

    suspend fun update(basketEntity: BasketEntity) {
        basketDao.update(basketEntity)
    }

    private var prodDatabase: BasketRoomDatabase? = null

    private fun initialiseDB(context: Context): BasketRoomDatabase? {
        val applicationScope = CoroutineScope(SupervisorJob())
        return BasketRoomDatabase.getDatabase(context, applicationScope)
    }
}