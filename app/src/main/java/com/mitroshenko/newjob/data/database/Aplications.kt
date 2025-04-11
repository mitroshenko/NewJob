package com.mitroshenko.newjob.data.database

import android.app.Application
import com.mitroshenko.newjob.data.repository.basket.BasketRepository
import com.mitroshenko.newjob.data.repository.favourites.FavouriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Applications : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val basketdatabase by lazy { BasketRoomDatabase.getDatabase(this, applicationScope) }
    val favouritedatabase by lazy { FavouriteRoomDatabase.getDatabase(this, applicationScope)}
        val basketrepository by lazy { BasketRepository(basketdatabase.dao()) }
        val favouriterepository by lazy { FavouriteRepository(favouritedatabase.favouritedao()) }
}

