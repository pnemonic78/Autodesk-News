package com.autodesk.news.data.local

import com.autodesk.news.data.NewsDataSource
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Observable

/**
 * Local database news data source.
 */
class NewsLocalDataSource(/*private val context: Context*/) : NewsDataSource {

    override fun getArticles(sourceId: String): Observable<List<NewsArticle>> {
        return Observable.empty()
    }

    override fun getTopHeadlines(country: String?, language: String?, sources: String?): Observable<List<NewsArticle>> {
        return Observable.empty()
    }
}