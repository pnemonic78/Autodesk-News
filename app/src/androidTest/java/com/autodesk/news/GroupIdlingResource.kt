package com.autodesk.news

import android.view.ViewGroup
import androidx.test.espresso.IdlingResource

/**
 * Request for idling resource when activity is busy doing I/O and the group is still empty.
 */
class GroupIdlingResource(private val group: ViewGroup) : IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null
    private var idle = false

    override fun getName(): String {
       return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        if (idle) return true

        checkGroup()

        if (idle) {
            callback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    private fun checkGroup() {
        if (group.childCount > 0) {
            idle = true
        }
    }
}