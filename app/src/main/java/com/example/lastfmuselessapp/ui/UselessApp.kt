package com.example.lastfmuselessapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.lastfmuselessapp.R
import com.example.lastfmuselessapp.ui.main.HomeScreen
import com.example.lastfmuselessapp.ui.main.HomeViewModel
import com.example.lastfmuselessapp.ui.onboarding.OnboardingScreen

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Onboarding : Screen("onboarding", R.string.onboarding)
    object Home : Screen("home", R.string.home)
    object Artist : Screen("artist/{artistId}", R.string.artist) {
        fun getArtistIdArgument(): String = "artistId"
    }
}

val bottomNavbarItems = listOf(
//    Screen.Onboarding,
    Screen.Home,
    Screen.Artist
)

@Composable
fun UselessApp() {

    val navController: NavHostController = rememberNavController()

    // TODO get correct thingy
    val isOnboardingDone = false

    Scaffold(bottomBar = {
        if (isOnboardingDone) {
            BottomNavigationBar(navController = navController)
        }
    }) {
        InitializeNavigation(navController = navController, isOnboardingDone)
    }
}

// TODO dodaj: https://developer.android.com/jetpack/compose/navigation#navigate-from-Compose

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavbarItems.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(text = stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId)
                    }
                })
        }
    }
}

@Composable
fun InitializeNavigation(navController: NavHostController, isOnboardingDone: Boolean = false) {
    NavHost(
        navController = navController,
        startDestination = if (isOnboardingDone) Screen.Home.route else Screen.Onboarding.route
    ) {
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(onFinishOnboardingClicked = { navController.navigate(Screen.Home.route) })
        }

        composable(route = Screen.Home.route) {

            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeUiState by homeViewModel.uiState.collectAsState()

            HomeScreen(homeUiState = homeUiState)
        }

        composable(
            route = Screen.Artist.route,
            arguments = listOf(navArgument(Screen.Artist.getArtistIdArgument()) {
                type = NavType.StringType
                nullable = true
//                defaultValue = "defaultValue"
            })
        ) { backstackEntry ->
            ArtistScreen(
                navController = navController,
                backstackEntry.arguments?.getString("artistId")
            )
        }
    }
}

@Composable
fun ArtistScreen(navController: NavController, artistId: String?) {
    Column {
        Text("Welcome to screen for artist with ID: $artistId")
    }
}