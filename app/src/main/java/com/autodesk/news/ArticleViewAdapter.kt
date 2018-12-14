package com.autodesk.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.news.dummy.DummyContent

/**
 * View adapter for articles.
 * @author moshe on 2018/12/14.
 */
class ArticleViewAdapter(
    private val parentActivity: ArticleListActivity,
    private val values: List<DummyContent.DummyItem>,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyContent.DummyItem
            if (twoPane) {
                val fragment = ArticleDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ArticleDetailFragment.ARG_ITEM_ID, item.id)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.article_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ArticleDetailActivity::class.java).apply {
                    putExtra(ArticleDetailFragment.ARG_ITEM_ID, item.id)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_content, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size
}