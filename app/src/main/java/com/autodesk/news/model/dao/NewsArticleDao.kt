package com.autodesk.news.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.Flowable

/**
 * News article DAO.
 */
@Dao
interface NewsArticleDao {

    /**
     * Select all news articles.
     *
     * @return all articles.
     */
    @Query("SELECT * FROM articles a")
    fun getArticles(): Flowable<List<NewsArticle>>

    /**
     * Insert an article in the database.
     * If the article already exists, replace it.
     *
     * @param article the article to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: NewsArticle)

    /**
     * Insert an article in the database.
     * If the article already exists, replace it.
     *
     * @param articles the list of articles to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<NewsArticle>)

    /**
     * Delete all articles.
     */
    @Query("DELETE FROM articles")
    fun deleteArticles()
}