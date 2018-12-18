package com.autodesk.news.data

import com.autodesk.news.data.local.NewsLocalDataSource
import com.autodesk.news.data.remote.NewsRemoteDataSource
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Observable

/**
 * News data repository.
 */
class NewsRepository(
    private val localDataSource: NewsLocalDataSource,
    private val remoteDataSource: NewsRemoteDataSource
) : NewsDataSource {
    override fun getArticles(sourceId: String): Observable<List<NewsArticle>> {
        return remoteDataSource.getArticles(sourceId)
    }

    override fun getTopHeadlines(country: String?, language: String?, sources: String?): Observable<List<NewsArticle>> {
        return remoteDataSource.getTopHeadlines(country, language, sources)
    }
}