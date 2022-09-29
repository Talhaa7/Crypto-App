package com.example.loodosandroid.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loodosandroid.base.ui.BaseViewModel
import com.example.loodosandroid.data.model.detail.CoinDetailResponse
import com.example.loodosandroid.domain.base.CryptoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) : BaseViewModel () {
    private val _coinDetails = MutableLiveData<CoinDetailResponse> ()

    val coinDetails : LiveData<CoinDetailResponse>
        get() = _coinDetails

    var coinId: String = ""
    var refreshTime: Long = -1

    init {
        if (refreshTime > 0){
            viewModelScope.launch {
                while (true) {

                    delay(refreshTime*1000)
                    if (coinId != ""){
                        getCoinDetails(coinId)
                    }



                }
            }
        }


    }

    fun getCoinDetails(coinId: String) {
        viewModelScope.launch {
            val detailResponse = cryptoRepository.getCoinDetails(coinId)
            _coinDetails.value = detailResponse

        }
    }

    fun getRefreshCoinDetails(){
        if (refreshTime > 0){
            viewModelScope.launch {
                request@ while (true) {

                    if (refreshTime <0) {
                        break@request
                    }


                    if (coinId != ""){
                        getCoinDetails(coinId)
                    }
                    delay(refreshTime*1000)

                }
            }
        }
    }
}