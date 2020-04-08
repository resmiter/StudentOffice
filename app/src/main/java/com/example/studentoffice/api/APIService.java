package com.example.studentoffice.api;

import com.example.studentoffice.model.AuthorizationResponse;
import com.example.studentoffice.model.News;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @GET("v2/top-headlines")
    Call<News> getNews(@Query("sources") String sources, @Query("apiKey") String apiKey);

    @FormUrlEncoded
    @POST("login")
    Call<AuthorizationResponse> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("check_access")
    Call<AuthorizationResponse> checkAccess(@Field("access_token") String access);

    @FormUrlEncoded
    @POST("refresh")
    Call<AuthorizationResponse> refreshToken(@Field("refresh_token") String refreshToken);
}
