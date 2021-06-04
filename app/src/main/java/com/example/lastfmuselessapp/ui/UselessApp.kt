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
    object Home : Screen("home", R.string.home)
    object Artist : Screen("artist/{artistId}", R.string.artist) {
        fun getArtistIdArgument(): String = "artistId"
        fun getRouteForArtistId(artistId: String) = "artist/$artistId"
    }

    object Library : Screen("library", R.string.library)
}

val bottomNavbarItems = listOf(
    Screen.Home,
    Screen.Library
)

@Composable
fun UselessApp() {

    val navController: NavHostController = rememberNavController()

    // TODO get correct thingy
    var isOnboardingDone by remember { mutableStateOf(false) }

    if (!isOnboardingDone) {
        OnboardingScreen(onFinishOnboardingClicked = {
            isOnboardingDone = true
        })
    } else {
        UselessAppHome(navController = navController)
    }
}

@Composable
fun UselessAppHome(navController: NavHostController) {

    var shouldShowNavbar by remember { mutableStateOf(true) }

    Scaffold(bottomBar = {
        if(shouldShowNavbar) {
            BottomNavigationBar(navController = navController)
        }
    }) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(route = Screen.Home.route) {

                val homeViewModel: HomeViewModel = hiltViewModel()
                val homeUiState by homeViewModel.uiState.collectAsState()

                HomeScreen(homeUiState = homeUiState, navController = navController)

                if(!shouldShowNavbar) {
                    shouldShowNavbar = true
                }
            }

            composable(route = Screen.Library.route) {
                Text(text = "Library, welcome!")

                if(!shouldShowNavbar) {
                    shouldShowNavbar = true
                }
            }

            composable(
                route = Screen.Artist.route,
                arguments = listOf(navArgument(Screen.Artist.getArtistIdArgument()) {
                    type = NavType.StringType
                    nullable = true
//                defaultValue = "defaultValue"
                })
            ) { backstackEntry ->

                shouldShowNavbar = false

                ArtistScreen(
                    navController = navController,
                    backstackEntry.arguments?.getString("artistId")
                )
            }
        }
    }
}

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
fun ArtistScreen(navController: NavController, artistId: String?) {
    Column {
        Text("Welcome to screen for artist with ID: $artistId")
    }
}