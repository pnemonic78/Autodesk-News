package com.autodesk.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.news.model.api.NewsArticle

/**
 * View adapter for articles.
 * @author moshe on 2018/12/14.
 */
class ArticleViewAdapter(
    private val listener: ArticleViewListener? = null,
    values: List<NewsArticle>? = null
) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    var items: List<NewsArticle> = values ?: emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
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
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    interface ArticleViewListener {
        fun onClickArticle(article: NewsArticle)
    }
}