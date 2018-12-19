package com.autodesk.news

import com.autodesk.news.data.NewsRepository
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Presenter of news articles.
 */
class ArticleListPresenter(private val repository: NewsRepository) : ArticleListContract.Presenter {

    private val disposables = CompositeDisposable()
    private var view: ArticleListContract.View? = null

    override fun attachView(view: ArticleListContract.View) {
        this.view = view
    }

    override fun detachView(view: ArticleListContract.View) {
        this.view = null
        disposables.dispose()
    }

    override fun onArticleClicked(article: NewsArticle) {
        view?.showArticleDetails(article)
    }

    override fun refreshArticles() {
        fetchArticles()
    }

    private fun fetchArticles() {
        repository.getTopHeadlines(sources = SOURCES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view?.showArticles(it) }
            .addTo(disposables)
    }

    companion object {
        //TODO let the user pick the source.
        private const val SOURCES = "cnn"
    }
}