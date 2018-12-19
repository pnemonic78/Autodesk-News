package com.autodesk.news.data

import com.autodesk.news.data.local.NewsLocalDataSource
import com.autodesk.news.data.remote.NewsRemoteDataSource
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Flowable

/**
 * News data repository.
 */
class NewsRepository(
    private val localDataSource: NewsLocalDataSource,
    private val remoteDataSource: NewsRemoteDataSource
) : NewsDataSource {

    override fun getTopHeadlines(country: String?, language: String?, sources: String?): Flowable<List<NewsArticle>> {
        return remoteDataSource.getTopHeadlines(country, language, sources)
    }
}