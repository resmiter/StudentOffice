package com.example.studentoffice.network

import android.content.Context
import android.content.SharedPreferences
import com.example.studentoffice.model.App
import com.example.studentoffice.model.AuthorizationResponse
import com.example.studentoffice.model.SharedPrefConst
import com.example.studentoffice.model.User

class Tokens {
    companion object {
        private val sPref: SharedPreferences =
            App.getAppContext()
                .getSharedPreferences(SharedPrefConst.KEY_NAME.toString(), Context.MODE_PRIVATE)

        init {
            initUserByToken()
        }

        private fun initUserByToken() {
            User.getUser().accessToken =
                sPref.getString(SharedPrefConst.KEY_ACCESS_TOKEN.toString(), "")
            User.getUser().refreshToken =
                sPref.getString(SharedPrefConst.KEY_REFRESH_TOKEN.toString(), "")
        }

        fun refreshTokens(response: AuthorizationResponse) {
            val editor: SharedPreferences.Editor = sPref.edit()
            editor.putString(SharedPrefConst.KEY_ACCESS_TOKEN.toString(), response.accessToken)
            editor.putString(SharedPrefConst.KEY_REFRESH_TOKEN.toString(), response.refreshToken)
            editor.apply()
            initUserByToken()
        }

        private fun refreshToken(onResponse: (AuthorizationResponse) -> Unit) {
            val serverRequests = ServerRequests()
            serverRequests.refreshToken {
                refreshTokens(it)
                onResponse(it)
            }
        }

        fun checkTokens(onResponse: (AuthorizationResponse) -> Unit) {
            val serverRequests = ServerRequests()
            serverRequests.checkAccess {
                if (it.success) {
                    onResponse(it)
                } else {
                    refreshToken(onResponse)
                }
            }
        }
    }
}