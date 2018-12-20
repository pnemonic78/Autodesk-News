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
 */
class ArticleListPresenter(private val service: NewsService) : ArticleListContract.Presenter {

    private val disposables = CompositeDisposable()
    private var view: ArticleListContract.View? = null
    private val articles: MutableList<NewsArticle> = ArrayList()
    private var pageIndex = 1

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

    override fun onLastArticleReached() {
        fetchArticles(pageIndex + 1)
    }

    override fun refreshArticles() {
        articles.clear()
        fetchArticles()
    }

    private fun fetchArticles(page: Int = 1) {
        service.getTopHeadlines(sources = SOURCES, page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                if (response.status == ArticlesResponse.STATUS_OK) {
                    pageIndex = page
                    articles.addAll(response.articles)
                    val newList = ArrayList(articles) //new list to force diff in adapter.
                    view?.showArticles(newList)
                }
            }
            .addTo(disposables)
    }

    companion object {
        //TODO let the user pick the source.
        private const val SOURCES = "cnn"
    }
}