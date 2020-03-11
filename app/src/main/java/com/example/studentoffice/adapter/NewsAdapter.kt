package com.example.studentoffice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentoffice.R
import com.example.studentoffice.model.Article
import com.squareup.picasso.Picasso

open class NewsAdapter(private val articles: List<Article>, private val onNewsListener: OnNewsListener) :
    RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news,
            parent,
            false
        )
        context = parent.context
        return NewsItemViewHolder(itemView, onNewsListener)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {

        holder.title.text = articles[position].title
        holder.description.text = articles[position].description
        holder.date.text = articles[position].publishedAt.take(10)

        Picasso.get().load(articles[position].urlToImage)
            .error(R.mipmap.person)
            .into(holder.image)

//        THIS IS ANIMATION OF ITEMS
//        holder.image.animation =
//            AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation)
//        holder.container.animation =
//            AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class NewsItemViewHolder(val newsView: View, val onNewsListener: OnNewsListener ) : RecyclerView.ViewHolder(newsView), View.OnClickListener {

        public var container: RelativeLayout = newsView.findViewById(R.id.containerNews)
        public var title: TextView = newsView.findViewById(R.id.newsTitle)
        public var description: TextView = newsView.findViewById(R.id.newsDescription)
        public var date: TextView = newsView.findViewById(R.id.newsDate)
        public var image: ImageView = newsView.findViewById(R.id.newsImg)
        private var justforSetOnClickListener = newsView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            onNewsListener.onNewsListener(adapterPosition)
        }
    }

    public interface OnNewsListener {
        fun onNewsListener(position: Int)
    }
}
