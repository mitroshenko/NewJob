package com.mitroshenko.newjob.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mitroshenko.newjob.data.repository.basket.BasketDao
import com.mitroshenko.newjob.data.repository.basket.BasketEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [BasketEntity::class], version = 1)
abstract class BasketRoomDatabase : RoomDatabase() {

    abstract fun dao(): BasketDao

    companion object {

        private const val DATABASE_NAME = "ProductDatabase"

        @Volatile
        private var INSTANCE: BasketRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BasketRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BasketRoomDatabase::class.java,
                    "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ProductDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
        private class ProductDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.dao())
                    }
                }
            }
        }
        suspend fun populateDatabase(basketDao: BasketDao) {
            basketDao.deleteAll()
        }
    }
}