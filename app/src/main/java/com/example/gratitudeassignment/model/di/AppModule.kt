package com.example.gratitudeassignment.model.di

import com.example.gratitudeassignment.model.data.remote.DailyZenApi
import com.example.gratitudeassignment.model.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
class AppModule {
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): DailyZenApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DailyZenApi::class.java)
    }
}