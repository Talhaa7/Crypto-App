package com.example.loodosandroid.base.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    /**
     * View Model for activity
     */
    @Inject
    lateinit var viewModel: VM

    /**
     * [ViewDataBinding] for activity
     */
    val viewBinding: B get() = _viewBinding!!

    private var _viewBinding: B? = null


    /**
     * Called to Initialize view data binding variables when fragment view is created.
     */
    @SuppressLint
    abstract fun onInitDataBinding()

    /**
     * Used as layout view id to create view binding
     */
    @LayoutRes
    abstract fun getContentViewId(): Int



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = DataBindingUtil.inflate(layoutInflater, getContentViewId(), null, false)
        setContentView(viewBinding.root)

    }


    override fun onResume() {
        super.onResume()

        if (!viewModel.viewModelStateSaved) {
            onInitDataBinding()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}