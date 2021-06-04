package com.example.lastfmuselessapp.ui.home

import com.example.lastfmuselessapp.domain.model.Artist
import com.example.lastfmuselessapp.domain.model.Resource

class HomeUiState(val topArtistsResource: Resource<List<Artist>> = Resource.Loading())
