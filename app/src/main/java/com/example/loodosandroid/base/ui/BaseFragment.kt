package com.example.loodosandroid.base.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.loodosandroid.base.extensions.safeNavigateFromNavController
import com.example.loodosandroid.base.model.NavigationCommand
import com.example.loodosandroid.domain.base.Event
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment() {

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
     * ProgressBar inside [Dialog] will be used on [BaseViewModel._loadingState] changes
     */
    var progressBarDialog: Dialog? = null


    var errorDialog: Dialog? = null

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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = DataBindingUtil.inflate(
            inflater,
            getContentViewId(),
            container,
            false
        )

        _viewBinding?.lifecycleOwner = viewLifecycleOwner

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigate(viewModel.onNavigateState)
        viewModel.viewModelStateSaved = true
        onInitDataBinding()

    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.viewModelStateSaved) {
            onInitDataBinding()
        }
    }

    protected open fun handleNavigation(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.ToDirection -> safeNavigateFromNavController(navigationCommand.directions)

            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }


    private fun observeNavigate(onNavigateState: LiveData<Event<NavigationCommand>>) {
        onNavigateState.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }





    override fun onDetach() {
        progressBarDialog?.dismiss()
        errorDialog?.dismiss()
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        errorDialog = null
        progressBarDialog = null
        _viewBinding = null
    }

}