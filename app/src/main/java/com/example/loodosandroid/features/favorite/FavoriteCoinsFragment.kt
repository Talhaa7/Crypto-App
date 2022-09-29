package com.example.loodosandroid.features.favorite

import android.graphics.Color
import com.example.loodosandroid.R
import com.example.loodosandroid.base.adapter.AdapterItemSelectListener
import com.example.loodosandroid.base.extensions.observe
import com.example.loodosandroid.base.ui.BaseFragment
import com.example.loodosandroid.databinding.FragmentFavoriteCoinsBinding
import com.example.loodosandroid.features.search.adapter.SearchCoinsListAdapter
import com.example.loodosandroid.features.search.model.SearchCoinsListUiModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteCoinsFragment : BaseFragment <FragmentFavoriteCoinsBinding, FavoriteCoinsViewModel>(){




    override fun onInitDataBinding() {
        viewBinding.rvFavoriteCoins.adapter = SearchCoinsListAdapter(
            OnCoinsListAdapterItemSelect()
        )

        viewModel.setAllFavoriteCoins()
        observeCoinsListLiveData()
    }

    override fun getContentViewId(): Int = R.layout.fragment_favorite_coins

    private inner class OnCoinsListAdapterItemSelect :
        AdapterItemSelectListener<SearchCoinsListUiModel> {
        override fun onAdapterItemSelected(listItem: SearchCoinsListUiModel) {
            viewModel.navigateToCoinDetailFragment(listItem.coinId)
        }
    }

    private fun observeCoinsListLiveData() {
        val adapter = viewBinding.rvFavoriteCoins.adapter as SearchCoinsListAdapter
        observe(viewModel.coinsList) {
            adapter.submitList(it)
        }
    }

}