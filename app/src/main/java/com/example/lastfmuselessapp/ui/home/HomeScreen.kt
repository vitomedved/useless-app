package com.example.lastfmuselessapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lastfmuselessapp.domain.model.Artist
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.ui.Screen

val screenEdgePadding = 16.dp

@Composable
fun HomeScreen(homeUiState: HomeUiState, navController: NavHostController) {
    if (homeUiState.topArtistsResource is Resource.Loading) {
        LoadingScreen(screenName = "Main Screen")
    } else {
        // TODO use TopArtistCarousel and other components with loading flag in order to render gray loading boxes and avoid separate screen for loading
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
        ) {

            // TODO refactor code
            if (homeUiState.topArtistsResource is Resource.Error) {
                Text(text = "Error happened: ${homeUiState.topArtistsResource.message}")
            } else if (homeUiState.topArtistsResource is Resource.Success) {
                homeUiState.topArtistsResource.data?.let {
                    TopArtistsCarousel(
                        artists = it,
                        modifier = Modifier.padding(
                            start = screenEdgePadding,
                            end = screenEdgePadding,
                            bottom = 24.dp,
                            top = 24.dp
                        )
                    ) { artistId ->
                        navController.navigate(Screen.Artist.getRouteForArtistId(artistId = artistId))
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(screenName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
        Text(
            text = "Loading $screenName...",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun TopArtistsCarousel(
    artists: List<Artist>,
    modifier: Modifier = Modifier,
    onArtistClicked: (String) -> Unit
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(artists) { item ->
            ArtistListItem(
                artist = item,
                modifier = Modifier
                    .clickable { onArtistClicked(item.id) }
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ArtistListItem(artist: Artist, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Artist name under me")
        Text(text = artist.name)
    }
}
