package com.example.studentoffice.ui.state.main.news

import Prin
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentoffice.R
import com.example.studentoffice.adapter.NewsAdapter
import com.example.studentoffice.model.Article
import com.example.studentoffice.model.News
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    private val KEY_ARTICLE: String = "key_article"
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var news: News
    private var isItemClick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.requestNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_news, container, false)
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

    override fun onResume() {
        super.onResume()
        isItemClick = false
    }

    private fun setListener() {
        refresh_layout_news.setOnRefreshListener {
            newsViewModel.requestNews()
        }
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
        if (activity == null || isItemClick) {
            return
        }
        isItemClick = true
        val intent = Intent(context, NewsViewingActivity::class.java)
        intent.putExtra(KEY_ARTICLE, article)
        startActivity(intent)
        activity!!.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}
