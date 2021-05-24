package com.example.lastfmuselessapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
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
import com.example.lastfmuselessapp.ui.onboarding.OnboardingScreen
import com.example.lastfmuselessapp.ui.onboarding.OnboardingUiState
import com.example.lastfmuselessapp.ui.onboarding.OnboardingViewModel

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Onboarding : Screen("onboarding/{index}", R.string.onboarding) {

        const val ARGUMENT_INDEX = "index"

        fun routeForIndex(index: Int?): String = route.replace("{index}", index?.toString() ?: "0")
    }

    object Welcome : Screen("welcome", R.string.welcome)
    object Artist : Screen("artist/{artistId}", R.string.artist)
}

val screenItems = listOf(
    //Screen.Onboarding,
    Screen.Welcome,
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

        screenItems.forEach { screen ->
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
        startDestination = if (isOnboardingDone) Screen.Welcome.route else Screen.Onboarding.route
    ) {
        composable(
            route = Screen.Onboarding.route,
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
                defaultValue = 0
            })
        ) { backStactEntry ->

            val onboardingViewModel: OnboardingViewModel = hiltViewModel()
            val onboardingUiState by onboardingViewModel.uiState.collectAsState()

            OnboardingScreen(
                navController = navController,
                onboardingUiState = onboardingUiState,
                onNextButtonClicked = {
                    // TODO navigate to next onboarding or welcome screen if onboarding is done
                    navController.navigate(
                        Screen.Onboarding.routeForIndex(
                            backStactEntry.arguments?.getInt(
                                Screen.Onboarding.ARGUMENT_INDEX
                            )?.plus(1)
                        )
                    )
                })
        }
        composable(route = Screen.Welcome.route) { WelcomeScreen(navController) }
        composable(
            route = Screen.Artist.route,
            arguments = listOf(navArgument("artistId") {
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
fun WelcomeScreen(navController: NavController) {
    Column {
        Text(text = "Welcome back...")
        Button(onClick = {
            navController.navigate("onBoardingScreen") {
                popUpTo("welcomeScreen") { inclusive = true }
                launchSingleTop = true
            }
        }) {
            Text(text = "Go to onboarding")
        }
    }
}

@Composable
fun ArtistScreen(navController: NavController, artistId: String?) {
    Column {
        Text("Welcome to screen for artist with ID: $artistId")
    }
}