package com.autodesk.news.data.local

import androidx.room.TypeConverter
import java.util.*

/**
 * Room data type converters.
 */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}