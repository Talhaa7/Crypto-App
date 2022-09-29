package com.example.loodosandroid.base.adapter

interface AdapterItemSelectListener<T> {

    /**
     * This method will be called when user select adapter item
     */
    fun onAdapterItemSelected(listItem: T)
}