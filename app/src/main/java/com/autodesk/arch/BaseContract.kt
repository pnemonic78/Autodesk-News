package com.autodesk.arch

/**
 * Base MVP contract.
 * @author Moshe on 2018/12/15.
 */
interface BaseContract {

    interface View {
    }

    interface Presenter<V : View> {
        fun attachView(view: V)
        fun detachView(view: V)
    }
}