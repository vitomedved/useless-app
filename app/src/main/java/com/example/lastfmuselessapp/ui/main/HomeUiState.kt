package com.example.lastfmuselessapp.ui.main

import com.example.lastfmuselessapp.domain.model.Artist
import com.example.lastfmuselessapp.domain.model.Resource

class HomeUiState(val artistResource: Resource<List<Artist>> = Resource.Loading())
