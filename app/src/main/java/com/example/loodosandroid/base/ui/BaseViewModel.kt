package com.example.loodosandroid.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.loodosandroid.base.model.NavigationCommand
import com.example.loodosandroid.domain.base.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel(){

    private val _onNavigateState = MutableLiveData<Event<NavigationCommand>>()

    val onNavigateState: LiveData<Event<NavigationCommand>>
        get() = _onNavigateState

    private val viewModelJob = SupervisorJob()

    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var viewModelStateSaved = false


    protected suspend fun <T> onUIThread(block: suspend CoroutineScope.() -> T):
            T = withContext(uiScope.coroutineContext) {
        block.invoke(this)
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    protected fun navigate(directions: NavDirections) {
        _onNavigateState.value = Event(NavigationCommand.ToDirection(directions))
    }
}