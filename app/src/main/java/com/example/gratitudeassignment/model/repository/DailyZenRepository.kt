package com.example.gratitudeassignment.model.repository

import android.util.Log
import com.example.gratitudeassignment.model.data.DataOrException
import com.example.gratitudeassignment.model.data.dao.DailyZenItemDao
import com.example.gratitudeassignment.model.data.local.DailyZenItemEntity
import com.example.gratitudeassignment.model.data.remote.DailyZen
import com.example.gratitudeassignment.model.data.remote.DailyZenApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DailyZenRepository @Inject constructor(private val api: DailyZenApi, private val dailyZenItemDao: DailyZenItemDao) {
    suspend fun getDailyZen(date: String): DataOrException<DailyZen, Boolean, Exception> {
        val response = try {
            api.getDailyZen(date = date)
        }catch (e: Exception){
            Log.d("Data", "Exception: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getDailyZen: $response")
        return DataOrException(data = response)

    }

//    Database

    fun getDailyZenItems(date: String): Flow<List<DailyZenItemEntity>> = dailyZenItemDao.getDailyZenItems(date = date)

    suspend fun insertDailyZenItem(dailyZenItemEntity: DailyZenItemEntity) = dailyZenItemDao.insertDailyZenItem(dailyZenItemEntity = dailyZenItemEntity)

    suspend fun deleteAllDailyZen() = dailyZenItemDao.deleteAllDailyZen()
}