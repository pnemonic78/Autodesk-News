package com.autodesk.news

/**
 * Presenter of a single news article.
 * @author Moshe on 2018/12/15.
 */
class ArticleDetailPresenter : ArticleDetailContract.Presenter {

    private var view: ArticleDetailContract.View? = null
    private var articleUrl: String? = null

    override fun attachView(view: ArticleDetailContract.View) {
        this.view = view
        maybeShowArticle()
    }

    override fun detachView(view: ArticleDetailContract.View) {
        this.view = null
    }

    override fun setArticleUrl(articleUrl: String?) {
        this.articleUrl = articleUrl
        maybeShowArticle()
    }

    private fun maybeShowArticle() {
        val viewer = view ?: return
        val url = articleUrl ?: return
        viewer.showArticle(url)
    }
}