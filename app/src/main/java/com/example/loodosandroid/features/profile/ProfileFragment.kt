package com.example.loodosandroid.features.profile

import com.example.loodosandroid.R
import com.example.loodosandroid.base.ui.BaseFragment
import com.example.loodosandroid.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :BaseFragment <FragmentProfileBinding, ProfileViewModel> ()  {
    override fun onInitDataBinding() {
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
            viewBinding.tvUserName.text = email
        }

        viewBinding.btnLogOut.setOnClickListener {
            Firebase.auth.signOut()
            viewModel.navigateToLoginFragment()
        }

    }

    override fun getContentViewId(): Int = R.layout.fragment_profile
}