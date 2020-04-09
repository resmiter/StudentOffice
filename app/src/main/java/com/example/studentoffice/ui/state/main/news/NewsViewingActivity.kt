package com.example.studentoffice.ui.state.main.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.studentoffice.R
import com.example.studentoffice.model.Article
import com.r0adkll.slidr.Slidr
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_viewing.*
import kotlinx.android.synthetic.main.content_viewing.*


class NewsViewingActivity : AppCompatActivity() {
    private val KEY_ARTICLE: String = "key_article"

    override fun onCreate(savedInstanceState: Bundle?) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppTheme_Dark_SlidrActivity)
        }
        super.onCreate(savedInstanceState)
        val article: Article? = intent.getParcelableExtra(KEY_ARTICLE)
        setContentView(R.layout.activity_news_viewing)
        setToolbar()
        Slidr.attach(this)
        if (article != null) {
            setArticle(article)
        }
    }

    private fun setArticle(article: Article) {
        Picasso.get().load(article.urlToImage).into(imageNewsViewing)
        newsTitleViewing.text = article.title
        newsDescriptionViewing.text = article.content
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar_viewing)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = getString(R.string.menu_news)
        }
        toolbar_viewing.setNavigationOnClickListener {
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
