package com.example.loodosandroid.base.model

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    /**
     * Handle Navigation Directions
     */
    data class ToDirection(val directions: NavDirections) : NavigationCommand()

    /**
     * Handle to pop back stack event
     */
    object Back : NavigationCommand()
}