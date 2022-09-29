package com.example.loodosandroid.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(
        this
    ) {
        it?.let { t -> observer(t) }
    }
}