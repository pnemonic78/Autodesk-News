package com.autodesk.news.data

import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Flowable

/**
 * News Data Source. (not to be confused with the News API Source)
 */
interface NewsDataSource {

    fun getTopHeadlines(
        country: String? = null,
        language: String? = null,
        sources: String? = null
    ): Flowable<List<NewsArticle>>

    fun getTopHeadlines(sourceId: String): Flowable<List<NewsArticle>> {
        return getTopHeadlines(null, null, sourceId)
    }

}