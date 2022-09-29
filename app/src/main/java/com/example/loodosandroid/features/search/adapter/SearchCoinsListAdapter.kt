package com.example.loodosandroid.features.search.adapter

import com.bumptech.glide.Glide
import com.example.loodosandroid.R
import com.example.loodosandroid.base.adapter.AdapterItemSelectListener
import com.example.loodosandroid.base.adapter.BaseListAdapter
import com.example.loodosandroid.databinding.CoinCardItemBinding
import com.example.loodosandroid.features.search.SearchCoinsViewModel
import com.example.loodosandroid.features.search.model.SearchCoinsListUiModel

class SearchCoinsListAdapter constructor(
    private val onClickListener: AdapterItemSelectListener<SearchCoinsListUiModel>
): BaseListAdapter<CoinCardItemBinding, SearchCoinsListUiModel>() {

    override fun getLayoutResId(): Int = R.layout.coin_card_item

    override fun bind(
        binding: CoinCardItemBinding,
        item: SearchCoinsListUiModel,
        itemPosition: Int
    ) {
        bindCoin(binding, item)
    }

    private fun bindCoin(binding: CoinCardItemBinding, item: SearchCoinsListUiModel) {
        binding.dateText.text = "Market Cap Rank: ${item.marketCapRank.toString()}"
        binding.mainText.text = item.coinName
        Glide.with(binding.image).load(item.coinImage).into(binding.image)

        binding.root.setOnClickListener {
            onClickListener.onAdapterItemSelected(item)
        }
    }
}