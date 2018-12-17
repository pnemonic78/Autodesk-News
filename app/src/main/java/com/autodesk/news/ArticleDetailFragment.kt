package com.autodesk.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.autodesk.news.ArticleDetailContract.Companion.ARG_ITEM_URL
import com.autodesk.news.model.api.NewsArticle
import kotlinx.android.synthetic.main.article_detail.view.*

/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a [ArticleListActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleDetailFragment : Fragment(), ArticleDetailContract.View {

    private val presenter = ArticleDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_URL)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                presenter.setArticleUrl(it.getString(ARG_ITEM_URL))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_detail, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = view.article_detail
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        webView.webViewClient = WebViewClient()

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView(this)
    }

    override fun showArticle(article: NewsArticle) {
        showArticle(article.url!!)
    }

    override fun showArticle(articleUrl: String) {
        view?.article_detail?.loadUrl(articleUrl)
    }
}
