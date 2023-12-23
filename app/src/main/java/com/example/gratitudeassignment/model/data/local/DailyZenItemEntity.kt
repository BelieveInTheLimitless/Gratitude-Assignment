package com.example.gratitudeassignment.model.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "daily_zen_item", primaryKeys = ["date", "id"])
data class DailyZenItemEntity (
    @ColumnInfo(name = "articleUrl")
    val articleUrl: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "dzImageUrl")
    val dzImageUrl: String,
    @ColumnInfo(name = "primaryCTAText")
    val primaryCTAText: String,
    @ColumnInfo(name = "sharePrefix")
    val sharePrefix: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "themeTitle")
    val themeTitle: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "date")
    val date: String
)