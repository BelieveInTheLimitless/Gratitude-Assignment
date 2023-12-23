package com.example.gratitudeassignment.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gratitudeassignment.model.data.DataOrException
import com.example.gratitudeassignment.model.data.local.DailyZenItemEntity
import com.example.gratitudeassignment.model.data.remote.DailyZen
import com.example.gratitudeassignment.model.repository.DailyZenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DailyZenRepository)
    : ViewModel(){

    val data: MutableState<DataOrException<DailyZen, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    private val _dataFromDb = MutableStateFlow<List<DailyZenItemEntity>>(emptyList())

    val dataFromDb = _dataFromDb.asStateFlow()

    private val date =  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))


    fun getCustomDailyZen(customDate: String){
        getDailyZen(date = customDate)
        getDailyZenItemsFromDb(date = customDate)
    }

    init {
        getCustomDailyZen(customDate = date)
    }

    private fun getDailyZenItemsFromDb(date:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDailyZenItems(date = date).distinctUntilChanged()
                .collect{ listOfDailyZenItem ->
                    if (listOfDailyZenItem.isNotEmpty()){
                        _dataFromDb.value = listOfDailyZenItem
                    }
                    else{
                        Log.d("DailyZenItemList", "List is Empty")
                    }
                }
        }
    }

    private fun getDailyZen(date: String){
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getDailyZen(date = date)
            data.value.data?.forEachIndexed { index, it ->
                insertDailyZenItem(DailyZenItemEntity(
                    articleUrl = it.articleUrl,
                    author = it.author,
                    dzImageUrl = it.dzImageUrl,
                    primaryCTAText = it.primaryCTAText,
                    sharePrefix = it.sharePrefix,
                    text = it.text,
                    themeTitle = it.themeTitle,
                    id = index,
                    date = date
                    ))
            }
            if (data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }

    private fun insertDailyZenItem(dailyZenItemEntity: DailyZenItemEntity) = viewModelScope.launch {
        repository.insertDailyZenItem(dailyZenItemEntity = dailyZenItemEntity)
    }

    fun deleteAllDailyZen() = viewModelScope.launch {
        repository.deleteAllDailyZen()
    }
}