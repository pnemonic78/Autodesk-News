package com.autodesk.news

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.autodesk.news.model.api.ArticlesResponse
import com.google.gson.GsonBuilder
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ArticlesTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.autodesk.news.debug", appContext.packageName)
    }

    @Test
    fun responseWithErrors() {
        val context = InstrumentationRegistry.getContext()
        //val input = context.resources.openRawResource(R.raw.errors)
        val input = context.assets.open("errors.json")
        assertNotNull(input)
        val reader = InputStreamReader(input)
        assertNotNull(reader)
        val gson = GsonBuilder().create()
        assertNotNull(gson)
        val response = gson.fromJson<ArticlesResponse>(reader, ArticlesResponse::class.java)
        assertNotNull(response)
        assertEquals("error", response.status)
        assertEquals("apiKeyMissing", response.code)
        assertEquals(
            "Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.",
            response.message
        )
        assertEquals(0, response.totalResults)
        assertNull(response.articles)
    }

    @Test
    fun responseTopHeadlines() {
        val context = InstrumentationRegistry.getContext()
        //val input = context.resources.openRawResource(R.raw.errors)
        val input = context.assets.open("top-headlines.json")
        assertNotNull(input)
        val reader = InputStreamReader(input)
        assertNotNull(reader)
        val gson = GsonBuilder().create()
        assertNotNull(gson)
        val response = gson.fromJson<ArticlesResponse>(reader, ArticlesResponse::class.java)
        assertNotNull(response)
        assertEquals("ok", response.status)
        assertNull(response.code)
        assertNull(response.message)
        assertEquals(36, response.totalResults)
        assertNotNull(response.articles)
        assertEquals(20, response.articles.size)

        // Test the first article
        val article = response.articles[0]
        assertNotNull(article)
        assertNotNull(article.source)
        assertEquals("cnn", article.source.id)
        assertEquals("CNN", article.source.name)
        assertEquals("Jay Croft and Madeline Holcombe, CNN", article.author)
        assertEquals("Authorities search for sender of global email bomb threats - CNN", article.title)
        assertEquals(
            "Authorities across three countries are trying to learn who sent dozens of email bomb threats Thursday afternoon, causing anxiety and business disruptions but no reported violence.",
            article.description
        )
        assertEquals("https://www.cnn.com/2018/12/14/us/email-bomb-threats-search/index.html", article.url)
        assertEquals("https://cdn.cnn.com/cnnnext/dam/assets/181204153836-hacking-super-tease.jpg", article.urlToImage)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse("2018-12-14T06:29:00Z")
        assertEquals(date, article.publishedAt)
        assertEquals(
            "(CNN) Authorities across four countries are trying to learn who sent dozens of email bomb threats Thursday afternoon, causing anxiety and business disruptions but no reported violence. Threats were reported across the United States, Canada, Australia and New … [+6437 chars]",
            article.content
        )
    }
}
