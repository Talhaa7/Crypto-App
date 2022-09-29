package com.example.loodosandroid.features

import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.loodosandroid.R
import com.example.loodosandroid.base.ui.BaseActivity
import com.example.loodosandroid.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var mAuth: FirebaseAuth

    override fun onInitDataBinding() {

    setupBottomNavigation()




    }

    override fun getContentViewId(): Int = R.layout.activity_main

    private fun setupBottomNavigation() {
        mAuth = Firebase.auth
        val user = mAuth.currentUser
        if (user != null) {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView)

            navHostFragment?.findNavController()
                ?.let { viewBinding.bottomNavigationView.setupWithNavController(it) }
        }
        showBottomNavigation()
    }

    fun showBottomNavigation(visibility: Boolean = true) {

        viewBinding.bottomNavigationView.isVisible = visibility
    }
}