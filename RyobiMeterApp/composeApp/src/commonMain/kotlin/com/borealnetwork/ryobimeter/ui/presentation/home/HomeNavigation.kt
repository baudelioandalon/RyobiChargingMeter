package com.borealnetwork.ryobimeter.ui.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.borealnetwork.ryobimeter.domain.navigation.HomeScreen

fun NavGraphBuilder.homeNavigation(
) {
    composable(
        route = HomeScreen.route
    ) {
        RyobiHomeScreen(
        )
    }
}