package com.example.studentoffice.ui.news

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.text.Layout
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentoffice.R
import com.example.studentoffice.adapter.NewsAdapter
import com.example.studentoffice.model.Article
import com.example.studentoffice.model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_news.view.*

class NewsFragment : Fragment(), NewsAdapter.OnNewsListener {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsAdapter: RecyclerView.Adapter<*>
    private lateinit var newsLayoutManager: RecyclerView.LayoutManager
    private lateinit var news: News
    private lateinit var onNewsListener: NewsAdapter.OnNewsListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        news = News()
        news.articles = ArrayList()
        onNewsListener = this
        newsLayoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(news.articles, onNewsListener)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newsViewModel =
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        newsViewModel.news.observe(this, Observer {
            if (activity != null) {
                newsRecyclerView = activity!!.findViewById<RecyclerView>(R.id.newsList).apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    news = it
                    adapter = NewsAdapter(news.articles, onNewsListener)
                }
            }
        })
        return root
    }

    override fun onNewsListener(position: Int) {
        if (context != null) {
            val newsDialog = Dialog(context!!)
            var article: Article = news.articles[position]
            newsDialog.setContentView(R.layout.dialog_news)


            val title: TextView = newsDialog.findViewById(R.id.newsTitleDialog)
            val description: TextView = newsDialog.findViewById(R.id.newsDescriptionDialog)
            val imageNews: ImageView = newsDialog.findViewById(R.id.imageNewsDialog)

            imageNews.animation = AnimationUtils.loadAnimation(context, R.anim.fade_transition_image_news_animation)
            title.animation = AnimationUtils.loadAnimation(context, R.anim.fade_transition_title_news_animation)
            description.animation = AnimationUtils.loadAnimation(context, R.anim.fade_transition_description_news_animation)

            title.movementMethod = ScrollingMovementMethod()
            description.movementMethod = title.movementMethod

            title.text = article.title
            description.text = article.content
            Picasso.get().load(article.urlToImage).into(imageNews)

            val display: Display = activity!!.windowManager!!.defaultDisplay
            val size = Point()
            display.getSize(size)
            newsDialog.window!!.setLayout(size.x, size.x)

            newsDialog.show()
        }
    }

}
