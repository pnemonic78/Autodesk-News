package com.autodesk.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autodesk.news.model.api.NewsArticle
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a [ArticleListActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: NewsArticle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_URL)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = null//TODO DummyContent.ITEM_MAP[it.getString(ARG_ITEM_URL)]
                activity?.toolbar_layout?.title = item?.title
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.article_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
           //TODO rootView.article_detail.text = it.details
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_URL = "item_url"
    }
}
