package com.example.studentoffice.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentoffice.R
import com.example.studentoffice.model.News

open class NewsAdapter(private val news: ArrayList<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news,
            parent,
            false
        )
        context = parent.context
        return NewsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
//        holder.title.text = news[position].title
//        holder.description.text = news[position].description
//        holder.date.text = news[position].date
//        holder.image.setImageResource(news[position].image)
        holder.image.animation = AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation)
        holder.container.animation = AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    class NewsItemViewHolder(val newsView: View) : RecyclerView.ViewHolder(newsView) {

        public var container: RelativeLayout= newsView.findViewById(R.id.containerNews)
        public var title: TextView = newsView.findViewById(R.id.newsTitle)
        public var description: TextView = newsView.findViewById(R.id.newsDescription)
        public var date: TextView = newsView.findViewById(R.id.newsDate)
        public var image: ImageView = newsView.findViewById(R.id.newsImg)

    }
}
