package com.android.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) {
        try {
            when (navigationFlow) {
                is NavigationFlow.RickAndMortyListToCharacterDetailsFlow -> navController.navigate(
                    NavGraphDirections.actionRickAndMortyListToCharacterDetailsFlow(navigationFlow.characterModel)
                )
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

    }
}