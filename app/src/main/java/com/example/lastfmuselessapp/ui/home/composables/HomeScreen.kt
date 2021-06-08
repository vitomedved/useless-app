package com.example.lastfmuselessapp.ui.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lastfmuselessapp.ui.composables.Label
import com.example.lastfmuselessapp.ui.home.HomeUiState
import com.example.lastfmuselessapp.ui.home.TopArtistsWorldwideCarousel

@Composable
fun HomeScreen(homeUiState: HomeUiState, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
    ) {

        Label(text = "Top artists worldwide")

        TopArtistsWorldwideCarousel(
            navController = navController,
            topArtistsWorldwideResource = homeUiState.topArtistsWorldwideResource
        )

        Spacer(Modifier.height(16.dp))

        Label(text = "Top tracks worldwide")

        TopTracksWorldwideCarousel(
            navController = navController,
            topTracksWorldwideResource = homeUiState.topTracksWorldwideResource
        )
    }
}
