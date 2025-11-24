package com.borealnetwork.ryobimeter


import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.borealnetwork.kmmuicore.ui.theme.PrimaryColor
import com.borealnetwork.ryobimeter.domain.navigation.HomeScreen
import com.borealnetwork.ryobimeter.ui.presentation.home.HomeViewModel
import com.borealnetwork.ryobimeter.ui.presentation.home.homeNavigation
import org.koin.compose.koinInject

@Composable
fun App(
    theme: ColorScheme = MaterialTheme.colorScheme.copy(
        primary = PrimaryColor
    ),
    navController: NavHostController,
    homeViewModel: HomeViewModel = koinInject()
) {
    MaterialTheme(
        colorScheme = theme
    ) {
//        val homeDevices = homeViewModel.stateDevices.collectAsStateWithLifecycle().value
//        homeViewModel.getData()
        homeViewModel.getData()

        NavHost(
            navController = navController,
            startDestination = HomeScreen.route,
        ) {
            homeNavigation()
        }

    }
}