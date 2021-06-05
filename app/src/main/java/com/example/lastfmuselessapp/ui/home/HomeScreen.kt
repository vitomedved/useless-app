package com.example.lastfmuselessapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.ui.Screen
import com.example.lastfmuselessapp.ui.composables.TopArtistsCarousel
import com.example.lastfmuselessapp.ui.composables.TopTracksCarousel

val screenEdgePadding = 16.dp

@Composable
fun HomeScreen(homeUiState: HomeUiState, navController: NavHostController) {

    // TODO use TopArtistCarousel and other components with loading flag in order to render gray loading boxes and avoid separate screen for loading
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
    ) {

        // TODO refactor code

        // TODO move this text to separate composable with other title Text, it's basically the same thing with different text
        Text(
            text = "Top artists worldwide",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp
            )
        )

        // TODO refactor this pls...
        when (homeUiState.topArtistsWorldwideResource) {
            is Resource.Loading -> LoadingResource(resourceName = "Top Artists")
            is Resource.Error -> Text(text = "Error happened: ${homeUiState.topArtistsWorldwideResource.message}")
            is Resource.Success -> {
                homeUiState.topArtistsWorldwideResource.data?.let { artistList ->
                    TopArtistsCarousel(
                        artistList = artistList,
                        modifier = Modifier
                            .padding(
                                start = screenEdgePadding,
                                top = screenEdgePadding,
                                end = screenEdgePadding
                            )
                            .fillMaxWidth()
                            .height(150.dp)
                    ) { artistId ->
                        if (artistId.isNotEmpty()) {
                            navController.navigate(Screen.Artist.getRouteForArtistId(artistId = artistId))
                        }
                    }
                }
            }
            else -> TODO("Not implemented state.")
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Top tracks worldwide",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(screenEdgePadding)
        )

        when (homeUiState.topTracksWorldwideResource) {
            is Resource.Loading -> LoadingResource(resourceName = "Top Tracks")
            is Resource.Error -> Text(text = "Error happened: ${homeUiState.topTracksWorldwideResource.message}")
            is Resource.Success -> {
                homeUiState.topTracksWorldwideResource.data?.let { trackList ->
                    TopTracksCarousel(
                        trackList = trackList,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    ) { artistId ->
                        // TODO
                    }
                }
            }
            else -> TODO("Not implemented state")
        }
    }
}

@Composable
fun LoadingResource(resourceName: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
        Text(
            text = "Loading $resourceName...",
            modifier = Modifier.padding(8.dp)
        )
    }
}

