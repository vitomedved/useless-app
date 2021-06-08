package com.example.lastfmuselessapp.ui.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.ui.composables.HorizontalClickableCarousel
import com.example.lastfmuselessapp.ui.composables.LoadingAnimation
import com.example.lastfmuselessapp.ui.composables.model.HorizontalCarouselItemModel

@Composable
fun TopTracksWorldwideCarousel(
    navController: NavHostController,
    topTracksWorldwideResource: Resource<List<HorizontalCarouselItemModel>>
) {

    when (topTracksWorldwideResource) {
        // TODO create gray loading animation for each item...
        is Resource.Loading -> LoadingAnimation(resourceName = "Top Tracks")
        // TODO think about how to show errors
        is Resource.Error -> Text(text = "Error happened: ${topTracksWorldwideResource.message}")
        is Resource.Success -> {
            topTracksWorldwideResource.data?.let { carouselItemList ->
                HorizontalClickableCarousel(
                    carouselItem = carouselItemList,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) { artistId ->
                    // TODO("Not implemented. What to do when this is clicked?")
                }
            }
        }
        else -> TODO("Not implemented state")
    }
}
