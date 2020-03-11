package com.example.studentoffice.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.Article
import com.example.studentoffice.model.News
import com.example.studentoffice.network.InternetConnection

class NewsViewModel : ViewModel() {

    //creatig, if does not exist
    private val _news = MutableLiveData<News>().apply {
        val internetConnection = InternetConnection()
        internetConnection.loadData(this)
    }

    //get
    val news: LiveData<News> = _news

}