package com.autodesk.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.autodesk.news.model.api.NewsArticle

/**
 * View adapter for articles.
 */
class ArticleViewAdapter(
    private val listener: ArticleViewListener? = null
) :
    ListAdapter<NewsArticle, ArticleViewHolder>(DIFF_CALLBACK) {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as NewsArticle
            listener?.onClickArticle(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_content, parent, false)
        return ArticleViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else {
            holder.clear()
        }

        if (position + 2 >= itemCount) {
            listener?.onLastArticleReached()
        }
    }

    interface ArticleViewListener {
        fun onClickArticle(article: NewsArticle)
        fun onLastArticleReached()
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
                // User properties may have changed if reloaded from the DB, but URL is fixed
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
                return oldItem == newItem
            }
        }
    }
}