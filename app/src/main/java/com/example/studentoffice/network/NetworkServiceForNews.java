package com.example.studentoffice.network;

import com.example.studentoffice.api.APIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceForNews {
    private static NetworkServiceForNews mInstance;
    private final long TIMEOUT = 10 * 1000L; //10 sec
    private final String BASE_URL = "https://newsapi.org/";
    private Retrofit mRetrofit;

    private NetworkServiceForNews() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static NetworkServiceForNews getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceForNews();
        }
        return mInstance;
    }

    private HttpLoggingInterceptor createLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(createLogger())
                .build();
    }

    APIService getJSONApiForNews() {
        return mRetrofit.create(APIService.class);
    }
}
