package com.example.loodosandroid.data

import com.example.loodosandroid.data.model.detail.CoinDetailResponse
import com.example.loodosandroid.data.model.search.SearchCoinsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoAppService {

    companion object {
        const val ENDPOINT = "https://api.coingecko.com/api/v3/"
    }

    @GET("search")
    suspend fun getSearchCoins(
        @Query("query") query: String
    ): SearchCoinsResponse

    @GET("coins/{id}")
    suspend fun getCoinDetails(
        @Path("id") coinId: String
    ) : CoinDetailResponse
}