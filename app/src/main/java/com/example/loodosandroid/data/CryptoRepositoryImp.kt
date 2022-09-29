package com.example.loodosandroid.data

import com.example.loodosandroid.data.model.detail.CoinDetailResponse
import com.example.loodosandroid.domain.base.CryptoRepository
import com.example.loodosandroid.features.search.model.SearchCoinsListUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoRepositoryImp @Inject constructor(private val cryptoAppService: CryptoAppService) : CryptoRepository{
    override suspend fun getSearchCoins(query: String): List<SearchCoinsListUiModel> {
        val searchCoins = withContext(Dispatchers.IO) {
            cryptoAppService.getSearchCoins(query)
        }
        return searchCoins.coins.map {
            SearchCoinsListUiModel(
                it.id,
                it.name,
                it.large,
                it.market_cap_rank,
            )
        }
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailResponse {
        val coinDetail = withContext(Dispatchers.IO) {
            cryptoAppService.getCoinDetails(coinId)
        }
        return coinDetail
    }
}