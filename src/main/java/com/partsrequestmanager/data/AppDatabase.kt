package com.partsrequestmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Request::class, Item::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun requestDao(): RequestDao
    abstract fun itemDao(): ItemDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "esc_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}