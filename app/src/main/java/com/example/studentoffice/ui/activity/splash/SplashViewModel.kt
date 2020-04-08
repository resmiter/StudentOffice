package com.example.studentoffice.ui.activity.splash

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.App
import com.example.studentoffice.model.AuthorizationResponse
import com.example.studentoffice.model.SharedPrefConst
import com.example.studentoffice.model.User
import com.example.studentoffice.network.ServerRequests

class SplashViewModel : ViewModel() {
    private val _response = MutableLiveData<AuthorizationResponse>()
    private var sPref: SharedPreferences = App.getAppContext().getSharedPreferences(
        SharedPrefConst.KEY_NAME.name,
        Context.MODE_PRIVATE
    )
    val response: MutableLiveData<AuthorizationResponse> = _response

    init {
        initUserByToken()
        //вынести логику проверки токенов в отдельный класс(возможно статический)
        checkTokens()
    }

    private fun initUserByToken() {
        User.getUser().accessToken = sPref.getString(SharedPrefConst.KEY_ACCESS_TOKEN.string, "")
        User.getUser().refreshToken = sPref.getString(SharedPrefConst.KEY_REFRESH_TOKEN.string, "")
    }

    private fun putTokensToPreferenses() {
        val editor: SharedPreferences.Editor = sPref.edit()
        editor.putString(SharedPrefConst.KEY_ACCESS_TOKEN.string, User.getUser().accessToken)
        editor.putString(SharedPrefConst.KEY_REFRESH_TOKEN.string, User.getUser().refreshToken)
        editor.apply()
    }

    private fun refreshToken() {
        val serverRequests = ServerRequests()
        serverRequests.refreshToken {
            User.getUser().accessToken = it.accessToken
            User.getUser().refreshToken = it.refreshToken
            putTokensToPreferenses()
            _response.value = it
        }
    }

    private fun checkTokens() {
        val serverRequests = ServerRequests()
        serverRequests.checkAccess {
            if (it.success) {
                _response.value = it
            } else {
                refreshToken()
            }
        }
    }
}