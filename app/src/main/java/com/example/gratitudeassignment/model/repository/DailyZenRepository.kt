package com.example.gratitudeassignment.model.repository

import android.util.Log
import com.example.gratitudeassignment.model.data.DataOrException
import com.example.gratitudeassignment.model.data.remote.DailyZen
import com.example.gratitudeassignment.model.data.remote.DailyZenApi
import javax.inject.Inject

class DailyZenRepository @Inject constructor(private val api: DailyZenApi) {
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
}