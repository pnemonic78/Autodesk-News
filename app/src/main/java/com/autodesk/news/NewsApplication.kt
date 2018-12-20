package com.autodesk.news

import android.app.Application
import com.autodesk.news.di.components.ApplicationComponent
import com.autodesk.news.di.components.DaggerApplicationComponent
import com.autodesk.news.di.modules.ApplicationModule

/**
 * News application.
 */
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}