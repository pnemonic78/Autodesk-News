package com.autodesk.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.article_detail.view.*

/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a [ArticleListActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleDetailFragment : Fragment() {

    /**
     * The content this fragment is presenting.
     */
    private var item: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_URL)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(ARG_ITEM_URL)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.article_detail, container, false)

        if (!item.isNullOrEmpty()) {
            val webView = rootView.article_detail
            webView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
            }

            webView.webViewClient = WebViewClient()
            webView.loadUrl(item)
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item URL that this fragment represents.
         */
        const val ARG_ITEM_URL = "item_url"
        /**
         * The fragment argument representing the item title that this fragment represents.
         */
        const val ARG_ITEM_TITLE = "item_title"
    }
}
