package com.example.loodosandroid.data.model.detail

data class CoinDetailResponse (
    val additional_notices: List<Any>,
    val coingecko_rank: Int,//
    val coingecko_score: Double,//
    val contract_address: String,
    val description: Description,
    val market_cap_rank: Int,//
    val market_data: MarketData,//
    val name: String,//
    val image: Image,
    val id: String,
)