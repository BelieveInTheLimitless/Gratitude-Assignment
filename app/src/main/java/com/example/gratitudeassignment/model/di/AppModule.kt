package com.example.gratitudeassignment.model.di

import android.content.Context
import androidx.room.Room
import com.example.gratitudeassignment.model.data.dao.DailyZenItemDao
import com.example.gratitudeassignment.model.data.dao.DailyZenItemDatabase
import com.example.gratitudeassignment.model.data.remote.DailyZenApi
import com.example.gratitudeassignment.model.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideDailyZeItemDatabase(dailyZenItemDatabase: DailyZenItemDatabase): DailyZenItemDao =
        dailyZenItemDatabase.dailyZenItemDao()

    @Singleton
    @Provides
    fun provideDailyZenItemDatabase(@ApplicationContext context: Context): DailyZenItemDatabase =
        Room.databaseBuilder(
            context,
            DailyZenItemDatabase::class.java,
            "daily_zen_item_database"
        ).fallbackToDestructiveMigration()
            .build()
}