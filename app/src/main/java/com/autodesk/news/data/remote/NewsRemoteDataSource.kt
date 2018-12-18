package com.autodesk.news.data.remote

import com.autodesk.news.api.NewsService
import com.autodesk.news.data.NewsDataSource
import com.autodesk.news.model.api.ArticlesResponse
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Observable

/**
 * Remote news data source.
 */
class NewsRemoteDataSource(private val service: NewsService) : NewsDataSource {

    override fun getArticles(sourceId: String): Observable<List<NewsArticle>> {
        return Observable.empty()
    }

    override fun getTopHeadlines(country: String?, language: String?, sources: String?): Observable<List<NewsArticle>> {
        return service.getTopHeadlines(country, language, sources)
            .flatMapObservable { response ->
                if (response.status == ArticlesResponse.STATUS_OK) {
                    Observable.just(response.articles)
                } else {
                    Observable.empty()
                }
            }
    }
}