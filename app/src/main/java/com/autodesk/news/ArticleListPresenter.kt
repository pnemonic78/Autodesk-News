package com.autodesk.news

import android.content.Context
import com.autodesk.news.model.api.ArticlesResponse
import com.autodesk.news.model.api.NewsArticle
import com.google.gson.GsonBuilder
import java.io.InputStreamReader

/**
 * Presenter of news articles.
 * @author Moshe on 2018/12/15.
 */
class ArticleListPresenter : ArticleListContract.Presenter {

    private var view: ArticleListContract.View? = null

    override fun attachView(view: ArticleListContract.View) {
        this.view = view
        fetchArticles()
    }

    override fun detachView(view: ArticleListContract.View) {
        this.view = null
    }

    override fun onArticleClicked(article: NewsArticle) {
        view?.showArticleDetails(article)
    }

    private fun fetchArticles() {
        val items = createDummyData()
        view?.showArticles(items)
    }

    private fun createDummyData(): List<NewsArticle> {
        val context: Context = view!!.getContext()
        val input = context.assets.open("top-headlines.json")
        val reader = InputStreamReader(input)
        val gson = GsonBuilder().create()
        val response = gson.fromJson<ArticlesResponse>(reader, ArticlesResponse::class.java)
        return response.articles
    }
}