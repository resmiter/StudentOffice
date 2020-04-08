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

class LoginViewModel : ViewModel() {
    private val _response = MutableLiveData<AuthorizationResponse>().apply {}
    val response: MutableLiveData<AuthorizationResponse> = _response

    private fun putTokensToPreferenses() {
        val sPref = App.getAppContext().getSharedPreferences(
            SharedPrefConst.KEY_NAME.name,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sPref.edit()
        editor.putString(SharedPrefConst.KEY_ACCESS_TOKEN.string, User.getUser().accessToken)
        editor.putString(SharedPrefConst.KEY_REFRESH_TOKEN.string, User.getUser().refreshToken)
        editor.apply()
    }

    fun login() {
        val serverRequestsKt = ServerRequests()
        serverRequestsKt.login {
            User.getUser().accessToken = it.accessToken
            User.getUser().refreshToken = it.refreshToken
            putTokensToPreferenses()
            _response.value = it
        }
    }
}