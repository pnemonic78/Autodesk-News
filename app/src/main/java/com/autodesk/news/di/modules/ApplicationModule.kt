package com.autodesk.news.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.autodesk.news.api.NewsService
import com.autodesk.news.data.NewsRepository
import com.autodesk.news.data.local.NewsDatabase
import com.autodesk.news.data.local.NewsLocalDataSource
import com.autodesk.news.data.remote.NewsRemoteDataSource
import dagger.Module
import dagger.Provides

/**
 * Application module.
 */
@Module
class ApplicationModule(val application: Application) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideNewsDatabase(context: Context): NewsDatabase {
        return Room.inMemoryDatabaseBuilder(context.applicationContext, NewsDatabase::class.java)
            .build()
    }

    @Provides
    internal fun provideLocaleDataSource(db: NewsDatabase): NewsLocalDataSource {
        return NewsLocalDataSource(db)
    }

    @Provides
    internal fun provideRemoteDataSource(service: NewsService): NewsRemoteDataSource {
        return NewsRemoteDataSource(service)
    }

    @Provides
    internal fun provideDataRepository(
        localDataSource: NewsLocalDataSource,
        remoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepository(localDataSource, remoteDataSource)
    }
}