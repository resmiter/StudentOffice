package com.example.studentoffice.ui.state.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.AuthorizationResponse
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