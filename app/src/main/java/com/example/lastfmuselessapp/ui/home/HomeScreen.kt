package com.example.lastfmuselessapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.model.view.HorizontalCarouselItemModel
import com.example.lastfmuselessapp.ui.Screen

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
                    HorizontalCarouselOfItems(
                        items = artistList.map {
                            HorizontalCarouselItemModel(
                                it.name,
                                it.name,
                                it.id
                            )
                        },
                        modifier = Modifier.padding(
                            start = screenEdgePadding,
                            end = screenEdgePadding,
                            bottom = 24.dp,
                            top = 24.dp
                        )
                    ) { artistId ->
                        if (artistId.isNotEmpty()) {
                            navController.navigate(Screen.Artist.getRouteForArtistId(artistId = artistId))
                        }
                    }
                }
            }
        }

        Text(
            text = "Top tracks worldwide",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp
            )
        )

        when (homeUiState.topTracksWorldwideResource) {
            is Resource.Loading -> LoadingResource(resourceName = "Top Tracks")
            is Resource.Error -> Text(text = "Error happened: ${homeUiState.topTracksWorldwideResource.message}")
            is Resource.Success -> {
                homeUiState.topTracksWorldwideResource.data?.let { trackList ->
                    HorizontalCarouselOfItems(
                        items = trackList.map {
                            HorizontalCarouselItemModel(
                                it.name,
                                "by ${it.artistName}",
                                it.id
                            )
                        },
                        modifier = Modifier.padding(
                            start = screenEdgePadding,
                            end = screenEdgePadding,
                            bottom = 24.dp,
                            top = 24.dp
                        )
                    ) { trackId ->
                        // NO-OP
                    }
                }
            }
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

@Composable
fun HorizontalCarouselOfItems(
    items: List<HorizontalCarouselItemModel>,
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit
) {

    LazyRow(
        modifier = modifier.background(color = MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(items) { item ->
            HorizontalCarouselItem(
                item = item,
                modifier = Modifier
                    .clickable { onItemClicked(item.id) }
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun HorizontalCarouselItem(item: HorizontalCarouselItemModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = item.topText, color = MaterialTheme.colors.onBackground)
        Text(text = item.bottomText, color = MaterialTheme.colors.onBackground)
    }
}

@Composable
fun TrackListItem(track: Track, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Track name under me")
        Text(text = track.name)
    }
}
