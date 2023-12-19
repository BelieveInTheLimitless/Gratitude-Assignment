package com.example.gratitudeassignment.model.data.remote

data class DailyZenItem(
    val articleUrl: String,
    val author: String,
    val bgImageUrl: String,
    val dzImageUrl: String,
    val dzType: String,
    val language: String,
    val primaryCTAText: String,
    val sharePrefix: String,
    val text: String,
    val theme: String,
    val themeTitle: String,
    val type: String,
    val uniqueId: String
)