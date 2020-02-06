package com.example.studentoffice.network;

import com.example.studentoffice.api.APIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private final String BASE_URL = "https://newsapi.org";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance  == null) {
            mInstance = new NetworkService();

        }
        return mInstance;
    }

    public APIService getJSONApi() {
        return mRetrofit.create(APIService.class);
    }
}
