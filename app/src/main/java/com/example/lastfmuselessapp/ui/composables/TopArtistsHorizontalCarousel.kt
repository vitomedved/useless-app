package com.example.lastfmuselessapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.domain.model.artist.Artist

@Composable
fun TopArtistsCarousel(
    artistList: List<Artist>,
    modifier: Modifier = Modifier,
    onArtistClicked: (String) -> Unit
) {

    LazyRow(
        modifier = modifier.background(color = MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(artistList) { artist ->
            HorizontalCarouselItem(
                artist.imageUrl,
                artist.name,
                modifier = Modifier.padding(4.dp)
            ) { onArtistClicked(artist.id) }
        }
    }
}
