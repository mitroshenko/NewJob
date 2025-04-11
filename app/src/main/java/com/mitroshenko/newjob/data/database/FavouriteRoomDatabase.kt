package com.mitroshenko.newjob.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mitroshenko.newjob.data.repository.favourites.FavouriteDao
import com.mitroshenko.newjob.data.repository.favourites.FavouriteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [FavouriteEntity::class], version = 1)
abstract class FavouriteRoomDatabase : RoomDatabase() {

    abstract fun favouritedao(): FavouriteDao

    companion object {

        private const val DATABASE_NAME = "FavouriteDatabase"

        @Volatile
        private var INSTANCE: FavouriteRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FavouriteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteRoomDatabase::class.java,
                    "favourite_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(FavouriteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
        private class FavouriteDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.favouritedao())
                    }
                }
            }
        }
        suspend fun populateDatabase(favouritedao: FavouriteDao) {
            favouritedao.deleteAll()
        }
    }
}