package com.example.lastfmuselessapp.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.ui.Screen
import com.example.lastfmuselessapp.ui.composables.HorizontalClickableCarousel
import com.example.lastfmuselessapp.ui.composables.model.HorizontalCarouselItemModel
import com.example.lastfmuselessapp.ui.composables.LoadingAnimation

@Composable
fun TopArtistsWorldwideCarousel(
    navController: NavHostController,
    topArtistsWorldwideResource: Resource<List<HorizontalCarouselItemModel>>
) {
    when (topArtistsWorldwideResource) {
        // TODO show loading animation for each item (gray animation of empty box) in carousel.
            // TODO Carousel of gray loading items? HorizontalLoadingCarousel or something?
        is Resource.Loading -> LoadingAnimation(resourceName = "Top Artists")
        // TODO define how to show error
        is Resource.Error -> Text(text = "Error happened: ${topArtistsWorldwideResource.message}")
        is Resource.Success -> {
            topArtistsWorldwideResource.data?.let { carouselItemList ->
                HorizontalClickableCarousel(
                    carouselItem = carouselItemList,
                    modifier = Modifier
                        .padding(
                            // TODO extract to resources, check out Compose resources documentation
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth()
                        // TODO resources
                        .height(150.dp)
                ) { artistId ->
                    // TODO what to do when ID is empty?
                    if (artistId.isNotEmpty()) {
                        navController.navigate(Screen.Artist.getRouteForArtistId(artistId = artistId))
                    }
                }
            }
        }
        else -> TODO("Not implemented state.")
    }
}
