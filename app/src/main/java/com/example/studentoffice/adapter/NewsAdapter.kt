package com.example.studentoffice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentoffice.R
import com.example.studentoffice.model.Article
import com.squareup.picasso.Picasso

open class NewsAdapter(
    private val articles: List<Article>,
    private val onClickOnArticle: (Article) -> Unit
) :
    RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news,
            parent,
            false
        )
        this.context = parent.context
        return NewsItemViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.title.text = articles[position].title
        holder.description.text = articles[position].description
        holder.date.text = articles[position].publishedAt.take(10)
        Picasso.get().load(articles[position].urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class NewsItemViewHolder(newsView: View) : RecyclerView.ViewHolder(newsView) {
        val title: TextView = newsView.findViewById(R.id.newsTitle)
        val description: TextView = newsView.findViewById(R.id.newsDescription)
        val date: TextView = newsView.findViewById(R.id.newsDate)
        val image: ImageView = newsView.findViewById(R.id.newsImg)

        init {
            newsView.setOnClickListener {
                onClickOnArticle(articles[adapterPosition])

            }
        }
    }
}
