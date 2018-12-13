package com.knews.android.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * News source POJO.
 */
@Entity(tableName = "sources")
data class NewsSource(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String? = null,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String? = null,

    @SerializedName("category")
    @ColumnInfo(name = "category")
    val category: String? = null,

    @SerializedName("language")
    @ColumnInfo(name = "language")
    val language: String? = null,

    @SerializedName("country")
    @ColumnInfo(name = "country")
    val country: String? = null
)