package com.example.loodosandroid.features.profile

import com.example.loodosandroid.base.ui.BaseViewModel
import com.example.loodosandroid.features.login.LoginFragmentDirections
import javax.inject.Inject

class ProfileViewModel @Inject constructor() :BaseViewModel() {

    fun navigateToLoginFragment(){
        navigate(ProfileFragmentDirections.navigateToLoginFragment())
    }
}