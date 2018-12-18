package com.autodesk.news


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
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
                )
            )
        )
        titleView.check(matches(isDisplayed()))
        titleView.check(matches(withText("Autodesk News")))

        val listViewMatcher = allOf(
            withId(R.id.article_list),
            IsInstanceOf.instanceOf(RecyclerView::class.java)
        )

        val listView = onView(
            listViewMatcher
        )
        listView.check(matches(isDisplayed()))
        listView.check(matches(hasChildCount(0)))
        val listViewGroup = activityTestRule.activity.findViewById<ViewGroup>(R.id.article_list)
        val listIdlingResource = GroupIdlingResource(listViewGroup)
        IdlingRegistry.getInstance().register(listIdlingResource)
        listView.check(matches(hasMinimumChildCount(1)))
        IdlingRegistry.getInstance().unregister(listIdlingResource)

        val ancestorIsListView =
            childAtPosition(
                childAtPosition(
                    listViewMatcher,
                    0
                ),
                0
            )

        val imageView = onView(
            allOf(
                withId(R.id.image),
                childAtPosition(ancestorIsListView, 0)
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.title),
                childAtPosition(ancestorIsListView, 1)
            )
        )
        textView.check(matches(isDisplayed()))
        textView.check(matches(not(withText(""))))

        val dateView = onView(
            allOf(
                withId(R.id.publishedAt),
                childAtPosition(ancestorIsListView, 2)
            )
        )
        dateView.check(matches(isDisplayed()))
        dateView.check(matches(not(withText(""))))

        val descriptionView = onView(
            allOf(
                withId(R.id.description),
                childAtPosition(ancestorIsListView, 3)
            )
        )
        descriptionView.check(matches(isDisplayed()))
        descriptionView.check(matches(not(withText(""))))
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
