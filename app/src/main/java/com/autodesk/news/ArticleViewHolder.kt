package com.autodesk.news

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_list_content.view.*

/**
 * View holder for an article.
 * @author moshe on 2018/12/14.
 */
class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val idView: TextView = view.id_text
    val contentView: TextView = view.content
}