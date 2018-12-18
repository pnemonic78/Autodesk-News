package com.autodesk.news.di.modules

import com.autodesk.news.api.NewsService
import com.autodesk.news.data.NewsRepository
import com.autodesk.news.data.local.NewsLocalDataSource
import com.autodesk.news.data.remote.NewsRemoteDataSource
import dagger.Module
import dagger.Provides

/**
 * Application module.
 */
@Module
class ApplicationModule {

    @Provides
    internal fun provideLocaleDataSource(): NewsLocalDataSource {
        return NewsLocalDataSource()
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