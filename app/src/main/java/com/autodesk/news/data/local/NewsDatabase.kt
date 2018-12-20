package com.autodesk.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.autodesk.news.model.api.NewsArticle
import com.autodesk.news.model.api.NewsSource
import com.autodesk.news.model.dao.NewsArticleDao
import com.autodesk.news.model.dao.NewsSourceDao

/**
 * News database.
 */
@Database(entities = [NewsArticle::class, NewsSource::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articlesDao(): NewsArticleDao
    abstract fun sourcesDao(): NewsSourceDao
}