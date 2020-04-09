package com.example.studentoffice.ui.state.main.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.studentoffice.R
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_news_viewing.*
import kotlinx.android.synthetic.main.app_bar_main.*

class NewsViewingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppTheme_Dark)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_viewing)
        setSupportActionBar(toolbar_viewing)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = getString(R.string.menu_news)
        }
        Slidr.attach(this)
    }
}
