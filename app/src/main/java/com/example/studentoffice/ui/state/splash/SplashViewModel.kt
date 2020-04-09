package com.example.studentoffice.ui.state.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.AuthorizationResponse
import com.example.studentoffice.network.Tokens

class SplashViewModel : ViewModel() {
    private val _response = MutableLiveData<AuthorizationResponse>()
    val response: MutableLiveData<AuthorizationResponse> = _response

    init {
        checkTokens()
    }

    private fun checkTokens() {
        Tokens.checkTokens {
            _response.value = it
        }
    }
}