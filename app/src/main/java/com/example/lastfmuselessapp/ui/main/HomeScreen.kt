package com.example.lastfmuselessapp.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lastfmuselessapp.model.Artist
import com.example.lastfmuselessapp.ui.Screen
import com.example.lastfmuselessapp.ui.onboarding.OnboardingScreen
import org.w3c.dom.Text

@Composable
fun HomeScreen(homeUiState: HomeUiState, navController: NavHostController) {
    Column {
        if (homeUiState.isLoading) {
            Text(text = "Loading screen...")
        } else {
            TopArtistsCarousel(onArtistClicked = { artistId ->
                navController.navigate(Screen.Artist.getRouteForArtistId(artistId = artistId))
            })
        }
    }
}

@Composable
fun TopArtistsCarousel(onArtistClicked: (Int) -> Unit) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val listOfItems = mutableListOf<Artist>()

        for (i in 0..5) {
            listOfItems.add(Artist(i, "Artist number $i"))
        }

        items(listOfItems) { item ->
            ArtistListItem(
                artist = item,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onArtistClicked(item.id) })
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
