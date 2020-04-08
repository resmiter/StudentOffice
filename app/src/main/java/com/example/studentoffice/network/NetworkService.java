package com.example.studentoffice.network;

import com.example.studentoffice.api.APIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkService {
    private static NetworkService mInstance;
    private final long TIMEOUT = 1 * 1000L; //5 sec
    private final String BASE_URL_REG = "http://46.61.183.21/";
    private Retrofit mRetrofit;

    private OkHttpClient createClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(createLogger())
                .build();
    }

    private HttpLoggingInterceptor createLogger(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_REG)
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static NetworkService getInstance() {
        if (mInstance  == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    APIService getJSONApi() {
        return mRetrofit.create(APIService.class);
    }
}
