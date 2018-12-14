package com.autodesk.news

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.autodesk.news.model.api.ArticlesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.InputStreamReader

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
}
