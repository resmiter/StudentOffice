package com.example.studentoffice.ui.activity.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.App
import com.example.studentoffice.model.AuthorizationResponse
import com.example.studentoffice.model.SharedPrefConst
import com.example.studentoffice.model.User
import com.example.studentoffice.network.ServerRequests
import com.example.studentoffice.network.Tokens

class LoginViewModel : ViewModel() {
    private val _response = MutableLiveData<AuthorizationResponse>()
    val response: MutableLiveData<AuthorizationResponse> = _response

    fun login() {
        val serverRequestsKt = ServerRequests()
        serverRequestsKt.login {
            Tokens.refreshTokens(it)
            _response.value = it
        }
    }
}