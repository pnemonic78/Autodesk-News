package com.autodesk.news

import com.autodesk.arch.BaseContract
import com.autodesk.news.model.api.NewsArticle

/**
 * MVP contract for list of news articles.
 */
interface ArticleListContract : BaseContract {
    interface View : BaseContract.View {
        fun showArticles(articles: List<NewsArticle>)
        fun showArticleDetails(article: NewsArticle)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onArticleClicked(article: NewsArticle)
        fun onLastArticleReached()
        fun refreshArticles()
    }
}