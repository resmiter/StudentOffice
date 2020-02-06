package com.example.studentoffice.api;

import com.example.studentoffice.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("/v2/top-headlines?sources=google-news&apiKey=9883864e464f4286b661fc86fc1f1c46")
    public Call<News> getNews();

}
