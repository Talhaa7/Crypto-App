package com.example.loodosandroid.domain.base

import android.annotation.SuppressLint

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an single event.
 * @see https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    @SuppressLint
    fun getContentIfNotHandled():
            T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

}