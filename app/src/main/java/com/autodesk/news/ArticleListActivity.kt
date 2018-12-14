package com.autodesk.news

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.news.model.api.ArticlesResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_article_list.*
import kotlinx.android.synthetic.main.article_list.*
import java.io.InputStreamReader

/**
 * An activity representing a list of Articles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ArticleDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ArticleListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (article_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(article_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val context: Context = this
        val input = context.assets.open("top-headlines.json")
        val reader = InputStreamReader(input)
        val gson = GsonBuilder().create()
        val response = gson.fromJson<ArticlesResponse>(reader, ArticlesResponse::class.java)
        val items = response.articles

        val adapter = ArticleViewAdapter(this, items, twoPane)
        recyclerView.adapter = adapter
    }
}
