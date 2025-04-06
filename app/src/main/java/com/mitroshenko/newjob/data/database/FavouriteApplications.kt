package com.mitroshenko.newjob.data.database

import android.app.Application
import com.mitroshenko.newjob.data.repository.favourites.FavouriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FavouriteApplications : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { FavouriteRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { FavouriteRepository(database.favouritedao()) }
}