package com.autodesk.news


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ArticleListActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(ArticleListActivity::class.java)

    @Test
    fun articleListActivityTest() {
        val titleView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withId(R.id.app_bar),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        titleView.check(matches(withText("Autodesk News")))

        val listView = onView(
            allOf(
                withId(R.id.article_list),
                isDisplayed()
            )
        )
        listView.check(matches(hasChildCount(0)))
        val listViewGroup = activityTestRule.activity.findViewById<ViewGroup>(R.id.article_list)
        val listIdlingResource = GroupIdlingResource(listViewGroup)
        IdlingRegistry.getInstance().register(listIdlingResource)
        listView.check(matches(hasMinimumChildCount(1)))
        IdlingRegistry.getInstance().unregister(listIdlingResource)
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
