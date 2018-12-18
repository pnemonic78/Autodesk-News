package com.autodesk.news

import com.autodesk.news.api.NewsService
import com.autodesk.news.model.api.ArticlesResponse
import com.autodesk.news.model.api.NewsArticle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Presenter of news articles.
 * @author Moshe on 2018/12/15.
 */
class ArticleListPresenter(private val service: NewsService) : ArticleListContract.Presenter {

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
        service.getTopHeadlines(sources = SOURCES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                if (response.status == ArticlesResponse.STATUS_OK) {
                    view?.showArticles(response.articles)
                }
            }
            .addTo(disposables)
    }

    companion object {
        //TODO let the user pick the source.
        private const val SOURCES = "cnn"
    }
}