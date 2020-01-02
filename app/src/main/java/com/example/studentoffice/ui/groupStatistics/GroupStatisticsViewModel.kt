package com.example.studentoffice.ui.groupStatistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupStatisticsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is group statistics Fragment"
    }
    val text: LiveData<String> = _text
}