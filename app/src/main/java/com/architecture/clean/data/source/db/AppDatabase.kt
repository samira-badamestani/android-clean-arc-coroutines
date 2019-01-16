package com.architecture.clean.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architecture.clean.domain.model.Food

@Database(entities = [Food::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "foods.db"
        const val VERSION = 1
    }
    abstract fun foodDao(): FoodDao
}