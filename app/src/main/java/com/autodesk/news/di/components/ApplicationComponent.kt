package com.autodesk.news.di.components

import com.autodesk.news.ArticleListActivity
import com.autodesk.news.NewsApplication
import com.autodesk.news.di.modules.ApplicationModule
import com.autodesk.news.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Application component.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(app: NewsApplication)

    fun inject(activity: ArticleListActivity)
}