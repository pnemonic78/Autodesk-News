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
    val name: String
)