package com.example.loodosandroid.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loodosandroid.base.ui.BaseViewModel
import com.example.loodosandroid.domain.base.CryptoRepository
import com.example.loodosandroid.features.search.model.SearchCoinsListUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchCoinsViewModel @Inject constructor(private val cryptoRepository: CryptoRepository): BaseViewModel() {

    private val _coinsList = MutableLiveData<List<SearchCoinsListUiModel>> ()

    val coinsList: LiveData<List<SearchCoinsListUiModel>>
        get() = _coinsList




    fun getSearchCoins(searchTerm: String) {
        viewModelScope.launch {
            val searchCoins = cryptoRepository.getSearchCoins(searchTerm)
            _coinsList.value = searchCoins
        }
    }

    fun navigateToDetailFragment(coinId: String) {
        navigate(SearchCoinsFragmentDirections.navigateToCoinDetailFragment(coinId))
    }

}