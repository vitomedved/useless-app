package com.example.lastfmuselessapp.ui.home

import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track

class HomeUiState(
    val topArtistsWorldwideResource: Resource<List<Artist>> = Resource.Loading(),
    val topTracksWorldwideResource: Resource<List<Track>> = Resource.Loading()
)
