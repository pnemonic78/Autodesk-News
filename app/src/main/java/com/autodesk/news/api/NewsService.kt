package com.autodesk.news.api

import com.autodesk.news.BuildConfig
import com.autodesk.news.model.api.ArticlesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

/**
 * News API Web Service.
 */
interface NewsService {

    @GET("articles")
    fun getArticles(@Query("source") source: String,
                    @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY): Observable<ArticlesResponse>

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String? = null,
                        @Query("language") language: String? = Locale.getDefault().language,
                        @Query("sources") sources: String? = null,
                        @Query("page") page: Int? = null,
                        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY): Observable<ArticlesResponse>
}