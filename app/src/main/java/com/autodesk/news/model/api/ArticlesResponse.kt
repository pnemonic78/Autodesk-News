package com.autodesk.news.model.api

import com.google.gson.annotations.SerializedName

/**
 * List of articles.
 */
class ArticlesResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<NewsArticle>
)