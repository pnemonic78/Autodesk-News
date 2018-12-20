package com.autodesk.news.data.local

import com.autodesk.news.data.NewsDataSource
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Flowable

/**
 * Local database news data source.
 */
class NewsLocalDataSource(private val database: NewsDatabase) : NewsDataSource {

    override fun getTopHeadlines(country: String?, language: String?, sources: String?): Flowable<List<NewsArticle>> {
        return database.articlesDao().getArticles()
    }
}