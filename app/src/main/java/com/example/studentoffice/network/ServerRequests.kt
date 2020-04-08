package com.example.studentoffice.network

import com.example.studentoffice.model.AuthorizationResponse
import com.example.studentoffice.model.News
import com.example.studentoffice.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerRequests {
    private val SOURCE: String = "google-news"
    private val APIKEY: String = "9883864e464f4286b661fc86fc1f1c46"

    private fun failLoginResponse(onResponse: (AuthorizationResponse) -> Unit, isFail: Boolean) {
        val authResponse = AuthorizationResponse()
        authResponse.isFail = isFail
        authResponse.success = false
        onResponse(authResponse)
    }

    fun login(onResponse: (AuthorizationResponse) -> Unit) {
        NetworkService.getInstance()
            .jsonApi
            .login(User.getUser().email, User.getUser().password)
            .enqueue(object : Callback<AuthorizationResponse?> {
                override fun onResponse(
                    call: Call<AuthorizationResponse?>,
                    response: Response<AuthorizationResponse?>
                ) {
                    val loginResponse: AuthorizationResponse? = response.body()
                    if (loginResponse != null && response.isSuccessful) {
                        onResponse(loginResponse)
                    } else {
                        failLoginResponse(onResponse, false)
                    }
                }

                override fun onFailure(call: Call<AuthorizationResponse?>, t: Throwable) {
                    failLoginResponse(onResponse, true)
                }
            })
    }

    fun refreshToken(onResponse: (AuthorizationResponse) -> Unit) {
        NetworkService.getInstance()
            .jsonApi
            .refreshToken(User.getUser().refreshToken)
            .enqueue(object : Callback<AuthorizationResponse?> {
                override fun onResponse(
                    call: Call<AuthorizationResponse?>,
                    response: Response<AuthorizationResponse?>
                ) {
                    val loginResponse: AuthorizationResponse? = response.body()
                    if (loginResponse != null && response.isSuccessful) {
                        onResponse(loginResponse)
                    } else {
                        failLoginResponse(onResponse, false)
                    }
                }

                override fun onFailure(call: Call<AuthorizationResponse?>, t: Throwable) {
                    failLoginResponse(onResponse, true)
                }
            })
    }

    fun checkAccess(onResponse: (AuthorizationResponse) -> Unit) {
        NetworkService.getInstance()
            .jsonApi
            .checkAccess(User.getUser().accessToken)
            .enqueue(object : Callback<AuthorizationResponse> {
                override fun onResponse(
                    call: Call<AuthorizationResponse>,
                    response: Response<AuthorizationResponse>
                ) {
                    val loginResponse = response.body()
                    if (loginResponse != null && response.isSuccessful) {
                        onResponse(loginResponse)
                    } else {
                        failLoginResponse(onResponse, false)
                    }
                }

                override fun onFailure(call: Call<AuthorizationResponse>, t: Throwable) {
                    failLoginResponse(onResponse, true)
                }
            })
    }

    fun requestNews(onResponse: (News) -> Unit) {
        NetworkServiceForNews.getInstance()
            .jsonApiForNews
            .getNews(SOURCE, APIKEY)
            .enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news: News? = response.body()
                    if (news != null && response.isSuccessful) {
                        onResponse(news)
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                }
            })
    }
}