package com.autodesk.news

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.news.api.NewsService
import com.autodesk.news.di.components.DaggerApplicationComponent
import com.autodesk.news.model.api.NewsArticle
import kotlinx.android.synthetic.main.activity_article_list.*
import kotlinx.android.synthetic.main.article_list.*
import javax.inject.Inject

/**
 * An activity representing a list of Articles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ArticleDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ArticleListActivity : AppCompatActivity(), ArticleViewAdapter.ArticleViewListener, ArticleListContract.View {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var presenter: ArticleListContract.Presenter
    private val adapter = ArticleViewAdapter(this)

    @Inject
    lateinit var service: NewsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.create().inject(this)

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

        presenter = ArticleListPresenter(service)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.refreshArticles()
    }

    override fun onClickArticle(article: NewsArticle) {
        presenter.onArticleClicked(article)
    }

    override fun onLastArticleReached() {
        presenter.onLastArticleReached()
    }

    override fun showArticles(articles: List<NewsArticle>) {
        adapter.submitList(articles)
    }

    override fun showArticleDetails(article: NewsArticle) {
        if (twoPane) {
            val fragment = ArticleDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ArticleDetailContract.ARG_ITEM_URL, article.url)
                    putString(ArticleDetailContract.ARG_ITEM_TITLE, article.title)
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.article_detail_container, fragment)
                    .commit()
        } else {
            val intent = Intent(this, ArticleDetailActivity::class.java).apply {
                putExtra(ArticleDetailContract.ARG_ITEM_URL, article.url)
                putExtra(ArticleDetailContract.ARG_ITEM_TITLE, article.title)
            }
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }
}
