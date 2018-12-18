package com.autodesk.news.data

import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Observable

/**
 * News Data Source. (not to be confused with the News API Source)
 */
interface NewsDataSource {

    fun getArticles(sourceId: String): Observable<List<NewsArticle>>

    fun getTopHeadlines(
        country: String? = null,
        language: String? = null,
        sources: String? = null
    ): Observable<List<NewsArticle>>

}