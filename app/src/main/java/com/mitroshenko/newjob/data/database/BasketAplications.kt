package com.mitroshenko.newjob.data.database

import android.app.Application
import com.mitroshenko.newjob.data.repository.basket.BasketRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BasketApplications : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ProductRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BasketRepository(database.dao()) }
}