package com.example.gratitudeassignment.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gratitudeassignment.model.data.DataOrException
import com.example.gratitudeassignment.model.data.remote.DailyZen
import com.example.gratitudeassignment.model.repository.DailyZenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DailyZenRepository)
    : ViewModel(){

    val data: MutableState<DataOrException<DailyZen, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    private val date =  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))

    fun getCustomDailyZen(customDate: String){
        getDailyZen(date = customDate)
    }

    init {
        getCustomDailyZen(customDate = date)
    }

    private fun getDailyZen(date: String){
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getDailyZen(date = date)
            if (data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }
}