package com.example.loodosandroid.domain.base

import com.example.loodosandroid.data.model.detail.CoinDetailResponse
import com.example.loodosandroid.features.search.model.SearchCoinsListUiModel

interface CryptoRepository {
    suspend fun getSearchCoins(query: String) : List<SearchCoinsListUiModel>

    suspend fun getCoinDetails(coinId: String) : CoinDetailResponse
}