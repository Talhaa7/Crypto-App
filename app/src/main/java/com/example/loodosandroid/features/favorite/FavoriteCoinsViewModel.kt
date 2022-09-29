package com.example.loodosandroid.features.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loodosandroid.base.ui.BaseViewModel
import com.example.loodosandroid.data.model.detail.CoinDetailResponse
import com.example.loodosandroid.domain.base.CryptoRepository
import com.example.loodosandroid.features.search.model.SearchCoinsListUiModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteCoinsViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) : BaseViewModel(){
    var listCoins = mutableListOf<String>()




    private val _coinsList = MutableLiveData<List<SearchCoinsListUiModel>> ()

    val coinsList: LiveData<List<SearchCoinsListUiModel>>
        get() = _coinsList

    private val db = Firebase.firestore
    private val user = Firebase.auth.currentUser
    private val userEmail = user!!.email!!



    fun setAllFavoriteCoins() {
        db.collection("crypto").document(userEmail).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    listCoins.clear()
                    listCoins.addAll((document["coinId"] as? List<String>?)?: emptyList())
                    getAllFavoriteCoins()
                }

            }
            .addOnFailureListener {  }
    }


    private fun getAllFavoriteCoins() {
        val listCoinsDef = mutableListOf<Deferred<CoinDetailResponse>>()

        viewModelScope.launch {

            listCoins.forEach { coins ->
                listCoinsDef.add(async {
                    val detailResponse = cryptoRepository.getCoinDetails(coins)
                    detailResponse
                })
            }

            val favoriteList = listCoinsDef.awaitAll()
            _coinsList.value = favoriteList.map {
                SearchCoinsListUiModel(
                    it.id,
                    it.name,
                    it.image.large,
                    it.market_cap_rank

                )
            }

            /*listCoins.forEach { coins ->
                val response = async {
                    val detailResponse = cryptoRepository.getCoinDetails(coins)
                    _coinDetails.value = detailResponse
                    println(detailResponse.name)
                }
            }*/


        }
    }
    fun navigateToCoinDetailFragment(coinId: String) {
        navigate(FavoriteCoinsFragmentDirections.navigateToCoinDetailFragment(coinId))
    }
}