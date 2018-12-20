package com.autodesk.news.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.autodesk.news.model.api.NewsArticle
import com.autodesk.news.model.api.NewsSource
import io.reactivex.Flowable

/**
 * News source DAO.
 */
@Dao
interface NewsSourceDao {

    /**
     * Select all news sources.
     *
     * @return all sources.
     */
    @Query("SELECT * FROM sources")
    fun getSources(): Flowable<List<NewsSource>>

    /**
     * Insert a sources into the database.
     * If the source already exists, replace it.
     *
     * @param source the source to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSource(source: NewsSource)

    /**
     * Insert sources into the database.
     * If the sources already exist, replace it.
     *
     * @param sources the list of sources to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(sources: List<NewsSource>)

    /**
     * Delete all sources.
     */
    @Query("DELETE FROM sources")
    fun deleteSources()
}