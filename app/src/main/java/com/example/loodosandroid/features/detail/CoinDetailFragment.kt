package com.example.loodosandroid.features.detail

import android.graphics.Color
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.loodosandroid.R
import com.example.loodosandroid.base.extensions.observe
import com.example.loodosandroid.base.ui.BaseFragment
import com.example.loodosandroid.databinding.FragmentCoinDetailBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding,CoinDetailViewModel> (){

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val favoriteCoins = mutableListOf<String>()
    private val db =Firebase.firestore
    private val user = Firebase.auth.currentUser
    private val userEmail = user!!.email!!

    override fun onInitDataBinding() {



        db.collection("crypto").document(userEmail).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    favoriteCoins.addAll((document["coinId"] as? List<String>?)?: emptyList())
                }

                if (favoriteCoins.contains(viewModel.coinId)) {
                    viewBinding.ivFavorite.setColorFilter(Color.BLUE)
                } else {
                    viewBinding.ivFavorite.setColorFilter(Color.GRAY)
                }
            }
            .addOnFailureListener {  }


        getCoinIdFromNavArgs()
        viewModel.getCoinDetails(viewModel.coinId)
        setCoinDetail()

        swipeRefresh = viewBinding.swipeRefresh

        swipeRefresh.setOnRefreshListener {
            viewModel.getCoinDetails(viewModel.coinId)

            swipeRefresh.isRefreshing = false
        }
        swipeRefresh.isRefreshing = false

        favoriteButtonClicked()

        setRefreshTime()



    }

    override fun getContentViewId(): Int = R.layout.fragment_coin_detail

    private fun setCoinDetail() {
        observe(viewModel.coinDetails) {
            val coinValueUsd = it.market_data.current_price.usd.toString()
            val changePercentage = it.market_data.price_change_percentage_24h
            viewBinding.detailCoinName.text = it.name
            viewBinding.detailCoinValue.text = "Usd : $coinValueUsd"
            viewBinding.detailPriceChangePercentageOverTheLast24h.text = "24h Change percentage : %${changePercentage}"
            viewBinding.detailDescriptionText.text = it.description.en
            Glide.with(viewBinding.detailCoinImage).load(it.image.large).into(viewBinding.detailCoinImage)
        }
    }

    private fun getCoinIdFromNavArgs() {
        navArgs<CoinDetailFragmentArgs>().value.apply {
            viewModel.coinId = coinId
        }
    }

    private fun favoriteButtonClicked() {
        viewBinding.ivFavorite.setOnClickListener {

            if (favoriteCoins.contains(viewModel.coinId)) {
                favoriteCoins.remove(viewModel.coinId)
                viewBinding.ivFavorite.setColorFilter(Color.GRAY)

            } else {
                favoriteCoins.add(viewModel.coinId)
                viewBinding.ivFavorite.setColorFilter(Color.BLUE)
            }

            db.collection("crypto").document(userEmail).set(hashMapOf(
                "coinId" to favoriteCoins
            ))
                .addOnSuccessListener{ }
                .addOnFailureListener { }
        }
    }

    private fun setRefreshTime() {

        viewBinding.refreshTime.addTextChangedListener {
            if (viewBinding.refreshTime.text.toString().isNotEmpty()) {
                viewModel.refreshTime = viewBinding.refreshTime.text.toString().toLong()
                viewModel.getRefreshCoinDetails()
            } else {
                viewModel.refreshTime = -1
            }

        }


    }
}