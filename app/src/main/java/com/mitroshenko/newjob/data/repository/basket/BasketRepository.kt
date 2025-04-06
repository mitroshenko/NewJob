package com.mitroshenko.newjob.data.repository.basket

import android.content.Context
import androidx.annotation.WorkerThread
import com.mitroshenko.newjob.data.database.ProductRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class BasketRepository(private val dao: Dao) {
    val allProducts: Flow<List<Entity>> = dao.getAlphabetizedProducts()

    @WorkerThread
    suspend fun insert(prod: Entity) {
        dao.insert(prod)
    }

    suspend fun delete(prod: Entity) {
        dao.delete(prod)
    }

    suspend fun update(prod: Entity) {
        dao.update(prod)
    }

    private var prodDatabase: ProductRoomDatabase? = null

    private fun initialiseDB(context: Context): ProductRoomDatabase? {
        val applicationScope = CoroutineScope(SupervisorJob())
        return ProductRoomDatabase.getDatabase(context, applicationScope)
    }
}