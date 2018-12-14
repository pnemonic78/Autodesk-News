package com.autodesk.news

import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.news.model.api.NewsArticle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.article_item.view.*

/**
 * View holder for an article.
 * @author moshe on 2018/12/14.
 */
class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val logoView: ImageView = view.image
    private val titleView: TextView = view.title
    private val publishedView: TextView = view.publishedAt
    private val descriptionView: TextView = view.description

    var article: NewsArticle? = null

    fun bind(article: NewsArticle) {
        this.article = article
        itemView.tag = article

        val context: Context = itemView.context

        Glide.with(context)
            .load(article.urlToImage)
            .into(logoView)

        titleView.text = article.title
        descriptionView.text = article.description
        publishedView.text = if (article.publishedAt != null)
            DateUtils.formatDateTime(
                context, article.publishedAt.time,
                DateUtils.FORMAT_SHOW_DATE.or(DateUtils.FORMAT_SHOW_TIME)
            ) else null
    }
}