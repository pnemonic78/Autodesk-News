package com.autodesk.news

import com.autodesk.arch.BaseContract
import com.autodesk.news.model.api.NewsArticle

/**
 * MVP contract for a single news article.
 */
interface ArticleDetailContract : BaseContract {
    interface View : BaseContract.View {
        fun showArticle(article: NewsArticle)
        fun showArticle(articleUrl: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun setArticleUrl(articleUrl: String?)
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