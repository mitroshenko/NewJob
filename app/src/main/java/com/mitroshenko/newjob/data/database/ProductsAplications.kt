package com.mitroshenko.newjob.data.database

import android.app.Application
import com.mitroshenko.newjob.data.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ProductsApplications : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ProductRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ProductRepository(database.dao()) }
}