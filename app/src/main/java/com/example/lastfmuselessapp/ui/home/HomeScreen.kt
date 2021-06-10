package com.example.lastfmuselessapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.ui.composables.HorizontalCarouselItem
import com.example.lastfmuselessapp.ui.composables.HorizontalScrollingCarousel
import com.example.lastfmuselessapp.ui.composables.Label
import com.example.lastfmuselessapp.ui.composables.LoadingAnimation

@Composable
fun HomeScreen(
    topArtistsWorldwide: Resource<List<Artist>>,
    topTracksWorldwide: Resource<List<Track>>,
    onArtistClicked: (String) -> Unit,
    onTrackClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
    ) {

        Label(text = "Top artists worldwide")

        val homeScreenCarouselHeight = Modifier.height(150.dp)

        HorizontalScrollingCarousel(
            modifier = homeScreenCarouselHeight,
            itemList = topArtistsWorldwide,
            loading = {
                LoadingAnimation(modifier = homeScreenCarouselHeight, resourceName = "Top Artists")
            },
            error = { message ->
                Text(modifier = homeScreenCarouselHeight, text = "Error happened: $message")
            },
            item = { artist ->
                HorizontalCarouselItem(
                    modifier = Modifier.padding(4.dp),
                    id = artist.id,
                    imageUrl = artist.imageUrl,
                    label = artist.name,
                    onItemClicked = onArtistClicked
                )
            }
        )

        Spacer(Modifier.height(16.dp))

        Label(text = "Top tracks worldwide")

        HorizontalScrollingCarousel(
            modifier = homeScreenCarouselHeight,
            itemList = topTracksWorldwide,
            loading = {
                LoadingAnimation(modifier = homeScreenCarouselHeight, resourceName = "Top Tracks")
            },
            error = { message ->
                Text(modifier = homeScreenCarouselHeight, text = "Error happened: $message")
            },
            item = { track ->
                HorizontalCarouselItem(
                    modifier = Modifier.padding(4.dp),
                    id = track.id,
                    imageUrl = track.imageUrl,
                    label = track.name,
                    onItemClicked = onTrackClicked
                )
            })
    }
}
