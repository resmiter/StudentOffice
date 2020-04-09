package com.example.studentoffice.ui.news

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.studentoffice.R
import com.example.studentoffice.adapter.NewsAdapter
import com.example.studentoffice.model.Article
import com.example.studentoffice.model.News
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrInterface
import com.r0adkll.slidr.model.SlidrPosition
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_news.*
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var news: News
    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_news, container, false)
        newsViewModel.requestNews()
        newsViewModel.news.observe(viewLifecycleOwner, Observer {
            this.news = it
            setNews()
            refresh_layout_news.isRefreshing = false
        })
        return root
    }

    override fun onStart() {
        super.onStart()
        setListener()
    }

    private fun setListener() {
        refresh_layout_news.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            newsViewModel.requestNews()
        })
    }

    private fun setNews() {
        if (activity == null) {
            return
        }
        newsRecyclerView = activity!!.newsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter(news.articles, ::onNewsListener)
        }
    }

    private fun onNewsListener(article: Article) {
        if (activity == null) {
            return
        }
        val newsViewingFragment =  NewsViewingFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment, newsViewingFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showDialog(article: Article) {
        if (context == null) {
            return
        }
        val newsDialog = Dialog(context!!)
        newsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        newsDialog.setContentView(R.layout.dialog_news)
        val imageNews: ImageView = newsDialog.imageNewsDialog
        val title: TextView = newsDialog.newsTitleDialog
        val description: TextView = newsDialog.newsDescriptionDialog

        imageNews.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_transition_image_news_animation)
        title.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_transition_title_news_animation)
        description.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_transition_description_news_animation)
        title.text = article.title
        description.text = article.content
        Picasso.get().load(article.urlToImage).into(imageNews)

        if (newsDialog.window != null) {
            newsDialog.window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        newsDialog.show()
    }
}
