package com.example.loodosandroid.features.search.model

import android.os.Parcelable
import com.example.loodosandroid.base.adapter.ListAdapterItem
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class SearchCoinsListUiModel (
    val coinId: String,
    val coinName : String,
    val coinImage: String,
    val marketCapRank: Int,
    override val id: String = UUID.randomUUID().toString()
    ) : ListAdapterItem, Parcelable