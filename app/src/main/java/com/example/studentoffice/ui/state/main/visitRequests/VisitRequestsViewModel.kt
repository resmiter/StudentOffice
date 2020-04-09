package com.example.studentoffice.ui.state.main.visitRequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VisitRequestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is visit requests Fragment"
    }
    val text: LiveData<String> = _text
}