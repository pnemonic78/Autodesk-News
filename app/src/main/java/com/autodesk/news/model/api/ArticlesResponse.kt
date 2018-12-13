package com.knews.android.model

import com.google.gson.annotations.SerializedName
import com.knews.android.data.NewsArticle

/**
 * List of articles.
 */
class ArticlesResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<NewsArticle>
)