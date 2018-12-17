package com.autodesk.news.di.components

import com.autodesk.news.ArticleListActivity
import com.autodesk.news.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Application component.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: ArticleListActivity)
}