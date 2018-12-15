package com.autodesk.news

import android.content.Context
import com.autodesk.arch.BaseContract
import com.autodesk.news.model.api.NewsArticle

/**
 * MVP contract for list of news articles.
 * @author Moshe on 2018/12/15.
 */
interface ArticleListContract : BaseContract {
    interface View : BaseContract.View {
        fun showArticles(articles: List<NewsArticle>)
        fun getContext(): Context
        fun showArticleDetails(article: NewsArticle)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onArticleClicked(article: NewsArticle)
    }
}