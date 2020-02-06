package com.example.studentoffice.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentoffice.R
import com.example.studentoffice.adapter.NewsAdapter
import com.example.studentoffice.model.News

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsAdapter: RecyclerView.Adapter<*>
    private lateinit var newsLayoutManager: RecyclerView.LayoutManager
    private lateinit var news: ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.news = ArrayList()
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())
        news.add(News())

        newsLayoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(news)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
//        val webView: WebView = root.findViewById(R.id.webNews)
        newsViewModel.text.observe(viewLifecycleOwner, Observer {
//            webView.loadUrl(it)
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsRecyclerView = activity!!.findViewById<RecyclerView>(R.id.newsList).apply {
            setHasFixedSize(true)
            layoutManager = newsLayoutManager
            adapter = newsAdapter
        }
    }
}