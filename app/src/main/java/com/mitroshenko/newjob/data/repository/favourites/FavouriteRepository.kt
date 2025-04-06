package com.mitroshenko.newjob.data.repository.favourites

import android.content.Context
import androidx.annotation.WorkerThread
import com.mitroshenko.newjob.data.database.FavouriteRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class FavouriteRepository(private val favouritedao: FavouriteDao) {
    val allFavourite: Flow<List<FavouriteEntity>> = favouritedao.getAlphabetizedFavourites()

    @WorkerThread
    suspend fun insert(favouriteEntity: FavouriteEntity) {
        favouritedao.insert(favouriteEntity)
    }

    suspend fun delete(favouriteEntity: FavouriteEntity) {
        favouritedao.delete(favouriteEntity)
    }

    suspend fun update(favouriteEntity: FavouriteEntity) {
        favouritedao.update(favouriteEntity)
    }

    private var favouriteDatabase: FavouriteRoomDatabase? = null

    private fun initialiseDB(context: Context): FavouriteRoomDatabase? {
        val applicationScope = CoroutineScope(SupervisorJob())
        return FavouriteRoomDatabase.getDatabase(context, applicationScope)
    }
}