package com.example.studentoffice.model

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun getAppContext(): Context = instance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        changeAppTheme()
    }

    private fun changeAppTheme() {
        val sPref = getAppContext().getSharedPreferences(
            SharedPrefConst.KEY_NAME.string,
            Context.MODE_PRIVATE
        )
        val themeId =
            sPref.getInt(SharedPrefConst.KEY_THEME.string, AppCompatDelegate.MODE_NIGHT_NO)
        if (AppCompatDelegate.getDefaultNightMode() != themeId) {
            AppCompatDelegate.setDefaultNightMode(themeId)
        }
    }
}