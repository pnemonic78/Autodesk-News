package com.autodesk.news.data.local

import com.autodesk.news.data.NewsDataSource
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Flowable

/**
 * Local in-memory news data source.
 */
class NewsLocalDataSource : NewsDataSource {

    private val cacheArticles: Map<Long, NewsArticle> = HashMap()

    override fun getTopHeadlines(country: String?, language: String?, sources: String?): Flowable<List<NewsArticle>> {
        return Flowable.empty()
    }
}