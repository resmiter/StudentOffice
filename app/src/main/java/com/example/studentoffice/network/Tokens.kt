package com.example.studentoffice.network

class Tokens {
//    companion object {
//        private val sPref: SharedPreferences =
//            App.getAppContext()
//                .getSharedPreferences(SharedPrefConst.KEY_NAME.toString(), Context.MODE_PRIVATE)
//
//        fun putTokensToPreferenses(response: TokensResponse) {
//            val editor: SharedPreferences.Editor = sPref.edit()
//            editor.putString(SharedPrefConst.KEY_ACCESS_TOKEN.toString(), response.accessToken)
//            editor.putString(SharedPrefConst.KEY_REFRESH_TOKEN.toString(), response.refreshToken)
//            editor.apply()
//        }
//
//        fun initUserByToken() {
//            User.getUser().accessToken =
//                sPref.getString(SharedPrefConst.KEY_ACCESS_TOKEN.toString(), "")
//            User.getUser().refreshToken =
//                sPref.getString(SharedPrefConst.KEY_REFRESH_TOKEN.toString(), "")
//        }
//
//        private fun refreshToken(onResponse: (TokensResponse) -> Unit) {
//            val serverRequests = ServerRequests()
//            serverRequests.refreshToken {
//                putTokensToPreferenses(it)
//                onResponse(it)
//            }
//        }
//
//        fun checkTokens(onResponse: (TokensResponse) -> Unit) {
//            val serverRequests = ServerRequests()
//            serverRequests.checkAccess {
//                if (it.success) {
//                    onResponse(it)
//                } else {
//                    refreshToken(onResponse)
//                }
//            }
//        }
//    }
}