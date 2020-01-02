package com.example.studentoffice.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "http://f-imitf.udsu.ru/"
    }
    val text: LiveData<String> = _text
}