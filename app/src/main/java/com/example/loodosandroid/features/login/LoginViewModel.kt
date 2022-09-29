package com.example.loodosandroid.features.login

import com.example.loodosandroid.base.ui.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun navigateToSearchCoinsFragment() {
        navigate(LoginFragmentDirections.navigateToSearchCoinsFragment())
    }
}