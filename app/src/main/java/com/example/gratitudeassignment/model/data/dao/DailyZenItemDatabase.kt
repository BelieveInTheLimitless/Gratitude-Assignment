package com.example.gratitudeassignment.model.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gratitudeassignment.model.data.local.DailyZenItemEntity

@Database(entities = [DailyZenItemEntity::class], version = 5, exportSchema = false)
abstract class DailyZenItemDatabase: RoomDatabase() {
    abstract fun dailyZenItemDao(): DailyZenItemDao
}