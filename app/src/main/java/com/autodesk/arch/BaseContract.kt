package com.autodesk.arch

/**
 * Base MVP contract.
 */
interface BaseContract {

    interface View {
    }

    interface Presenter<V : View> {
        fun attachView(view: V)
        fun detachView(view: V)
    }
}