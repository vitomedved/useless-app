package com.example.lastfmuselessapp.ui.home

import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.ui.composables.model.HorizontalCarouselItemModel

class HomeUiState(
    val topArtistsWorldwideResource: Resource<List<HorizontalCarouselItemModel>> = Resource.Loading(),
    val topTracksWorldwideResource: Resource<List<HorizontalCarouselItemModel>> = Resource.Loading()
)
