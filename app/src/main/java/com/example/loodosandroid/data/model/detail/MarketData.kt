package com.example.loodosandroid.data.model.detail

data class MarketData(
    val ath: Ath,
    val ath_change_percentage: AthChangePercentage,
    val atl: Atl,
    val atl_change_percentage: AtlChangePercentage,
    val current_price: CurrentPrice,
    val high_24h: High24h,
    val last_updated: String,
    val low_24h: Low24h,
    val market_cap_change_24h: Double,
    val market_cap_rank: Int,
    val mcap_to_tvl_ratio: Any,
    val price_change_24h: Double,
    val price_change_24h_in_currency: PriceChange24hInCurrency,
    val total_volume: TotalVolume,
    val price_change_percentage_24h: Double
)