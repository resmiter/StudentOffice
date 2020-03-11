package com.example.studentoffice.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import com.example.studentoffice.R
import com.squareup.picasso.Picasso

class ViewANewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_a_news)

        val imageView: ImageView = findViewById(R.id.imageNewsInActivity)
        val title: TextView = findViewById(R.id.newsTitleInActivity)
        val content: TextView = findViewById(R.id.newsDescriptionInActivity)

        if (intent != null) {
            Picasso.get().load(intent.getStringExtra("image_url")).into(imageView)
            title.text = intent.getStringExtra("title")
            var string: String = intent.getStringExtra("content")
            string += intent.getStringExtra("content")
            string += intent.getStringExtra("content")
            string += intent.getStringExtra("content")
            string += intent.getStringExtra("content")
//            content.text = intent.getStringExtra("content")
                content.text = string
            content.setMovementMethod(ScrollingMovementMethod())
        }


    }
}
