package com.example.gratitudeassignment.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gratitudeassignment.model.data.local.DailyZenItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyZenItemDao {

    @Query("SELECT * FROM daily_zen_item WHERE date = :date ORDER BY id ASC")
    fun getDailyZenItems(date: String): Flow<List<DailyZenItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyZenItem(dailyZenItemEntity: DailyZenItemEntity)

    @Query("DELETE FROM daily_zen_item")
    suspend fun deleteAllDailyZen()

}