package com.autodesk.news.model.api

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * News article POJO.
 */
@Entity(tableName = "articles")
data class NewsArticle(
    @PrimaryKey
    var articleId: Long,

    @SerializedName("source")
    @Embedded(prefix = "source")
    var source: NewsSource,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    var author: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null,

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