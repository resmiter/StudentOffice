package com.example.studentoffice.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.News
import com.example.studentoffice.network.ServerRequests

class NewsViewModel : ViewModel() {
    private val _news = MutableLiveData<News>()
    val news: LiveData<News> = _news

    fun requestNews(){
        val serverRequests = ServerRequests()
        serverRequests.requestNews {
            _news.value = it
        }
    }
} 