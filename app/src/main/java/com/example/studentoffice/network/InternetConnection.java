package com.example.studentoffice.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.studentoffice.model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InternetConnection {

    private final String NEWS_ID = "top-headlines?sources=google-news&apiKey=9883864e464f4286b661fc86fc1f1c46";

    public void loadData(final MutableLiveData<News> newsMutableLiveData) {
        NetworkService.getInstance()
                .getJSONApi()
                .getNews()
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.isSuccessful()) {
                            News news = response.body();
                            newsMutableLiveData.postValue(news);
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                    }
                });
    }
}
