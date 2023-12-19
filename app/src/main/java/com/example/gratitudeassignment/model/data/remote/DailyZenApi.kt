package com.example.gratitudeassignment.model.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface DailyZenApi {
    @GET(value = "prod/dailyzen")
    suspend fun getDailyZen(
        @Query("date") date: String,
        @Query("version") version: String = "2"
    ): DailyZen
}