package com.example.studentoffice.ui.requestsToAdmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RequestsToAdminViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is requests to admin Fragment"
    }
    val text: LiveData<String> = _text
}