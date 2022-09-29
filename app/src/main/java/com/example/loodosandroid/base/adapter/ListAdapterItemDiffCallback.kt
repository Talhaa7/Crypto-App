package com.example.loodosandroid.base.adapter

import androidx.recyclerview.widget.DiffUtil

class ListAdapterItemDiffCallback<T : ListAdapterItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T):
            Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T):
            Boolean = oldItem == newItem
}