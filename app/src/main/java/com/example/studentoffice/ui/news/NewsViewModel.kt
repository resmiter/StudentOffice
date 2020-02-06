package com.example.studentoffice.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.Article
import com.example.studentoffice.model.News
import com.example.studentoffice.network.InternetConnection

class NewsViewModel : ViewModel() {

    private lateinit var newssss: MutableLiveData<News>

    private val _news = MutableLiveData<News>().apply {
        val internetConnection = InternetConnection()
        internetConnection.loadData(this)
    }

    val news: LiveData<News> = _news

}