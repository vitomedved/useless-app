package com.example.lastfmuselessapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.lastfmuselessapp.model.NavbarItem
import com.example.lastfmuselessapp.model.Screen
import com.example.lastfmuselessapp.ui.discover.DiscoverScreen
import com.example.lastfmuselessapp.ui.discover.DiscoverViewModel
import com.example.lastfmuselessapp.ui.home.HomeScreen
import com.example.lastfmuselessapp.ui.home.HomeViewModel
import com.example.lastfmuselessapp.ui.onboarding.OnboardingScreen

val bottomNavbarItems = listOf(
    NavbarItem(Screen.Home, Icons.Default.Home),
    NavbarItem(Screen.Discover, Icons.Default.Search),
    NavbarItem(Screen.Library, Icons.Default.List)
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
        UselessAppBody(navController = navController)
    }
}

@Composable
fun UselessAppBody(navController: NavHostController) {

    var shouldShowNavbar by remember { mutableStateOf(true) }

    Scaffold(bottomBar = {
        if (shouldShowNavbar) {
            BottomNavigationBar(navController = navController)
        }
    }) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(route = Screen.Home.route) {

                val homeViewModel: HomeViewModel = hiltViewModel()
                val topArtistsWorldwide by homeViewModel.topTracksWorldwide
                val topTracksWorldwide by homeViewModel.topArtistsWorldwide

                HomeScreen(topArtistsWorldwide = topArtistsWorldwide,
                    topTracksWorldwide = topTracksWorldwide,
                    onArtistClicked = { artistId ->
                        // TODO what if ID is missing?
                        if (artistId.isNotEmpty()) {
                            navController.navigate(Screen.Artist.getRouteForArtistId(artistId = artistId))
                        }
                    },
                    onTrackClicked = { trackId ->
                        // TODO
                    })

                if (!shouldShowNavbar) {
                    shouldShowNavbar = true
                }
            }

            composable(route = Screen.Discover.route) {

                val discoverViewModel: DiscoverViewModel = hiltViewModel()
                val searchText by discoverViewModel.searchText
                val focused by discoverViewModel.focused

                DiscoverScreen(
                    searchText = searchText,
                    focused = focused,
                    onSearchTextChanged = discoverViewModel::onSearchTextChanged,
                    onSearchTextCleared = discoverViewModel::onSearchTextCleared,
                    onFocusChanged = discoverViewModel::onFocusChanged
                )
            }

            composable(route = Screen.Library.route) {
                Text(text = "Library, welcome!")

                if (!shouldShowNavbar) {
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
                    artistId = backstackEntry.arguments?.getString("artistId")
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

        bottomNavbarItems.forEach { navbarItem ->
            BottomNavigationItem(
                icon = { Icon(imageVector = navbarItem.icon, contentDescription = null) },
                label = { Text(text = stringResource(id = navbarItem.screen.nameResource)) },
                selected = currentRoute == navbarItem.screen.route,
                onClick = {
                    navController.navigate(navbarItem.screen.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId)
                    }
                })
        }
    }
}

@Composable
fun ArtistScreen(artistId: String?) {
    Column {
        Text("Welcome to screen for artist with ID: $artistId")
    }
}