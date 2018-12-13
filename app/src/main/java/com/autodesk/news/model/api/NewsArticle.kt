package com.autodesk.news.model.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * News article POJO.
 */
@Entity(tableName = "articles")
data class NewsArticle(
    @SerializedName("source")
    @ColumnInfo(name = "source")
    val source: NewsSource,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String? = null,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: Date? = null,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String? = null
)