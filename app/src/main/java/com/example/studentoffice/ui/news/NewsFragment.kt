package com.example.studentoffice.ui.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentoffice.R
import com.example.studentoffice.adapter.NewsAdapter
import com.example.studentoffice.model.Article
import com.example.studentoffice.model.News

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onNewsListener(position: Int) {
        Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()

        var article: Article = news.articles[position]
        val intent = Intent(context, ViewANewsActivity::class.java)
        startActivity(intent);

    }

}