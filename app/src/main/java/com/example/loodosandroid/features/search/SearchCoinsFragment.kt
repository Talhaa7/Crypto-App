package com.example.loodosandroid.features.search

import androidx.appcompat.widget.SearchView
import com.example.loodosandroid.R
import com.example.loodosandroid.base.adapter.AdapterItemSelectListener
import com.example.loodosandroid.base.extensions.observe
import com.example.loodosandroid.base.ui.BaseFragment
import com.example.loodosandroid.databinding.FragmentSearchCoinsBinding
import com.example.loodosandroid.features.search.adapter.SearchCoinsListAdapter
import com.example.loodosandroid.features.search.model.SearchCoinsListUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCoinsFragment : BaseFragment<FragmentSearchCoinsBinding, SearchCoinsViewModel>() {
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel

        viewBinding.rvSearchCoins.adapter = SearchCoinsListAdapter(
            OnCoinsListAdapterItemSelect()
        )


        textChangeListener()



       observeCoinsListLiveData()
    }

    override fun getContentViewId(): Int = R.layout.fragment_search_coins


    private inner class OnCoinsListAdapterItemSelect :
        AdapterItemSelectListener<SearchCoinsListUiModel> {
        override fun onAdapterItemSelected(listItem: SearchCoinsListUiModel) {
            viewModel.navigateToDetailFragment(listItem.coinId)
        }
    }

    private fun observeCoinsListLiveData() {
        val adapter = viewBinding.rvSearchCoins.adapter as SearchCoinsListAdapter
        observe(viewModel.coinsList) {
            adapter.submitList(it)
        }
    }

    private fun textChangeListener() {

        var submitText = ""
        var changeText = ""
        viewBinding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        submitText = it
                        if (submitText != changeText) {
                            viewModel.getSearchCoins(it)

                        }

                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText == null || newText.isEmpty() || newText.length < 2) {

                        val adapter = viewBinding.rvSearchCoins.adapter as SearchCoinsListAdapter
                        adapter.submitList(emptyList())
                    } else {
                        changeText = newText
                        viewModel.getSearchCoins(newText)
                    }

                    return false
                }

            }
        )
    }



}