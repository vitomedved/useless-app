package com.example.lastfmuselessapp.domain.repository

import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.Resource

interface ArtistRepository {

    suspend fun getTopArtists(numberOfTopArtists: Int): Resource<List<Artist>>

    suspend fun fetchArtist(value: String, numberOfResults: Int? = 20): Resource<List<Artist>>
}
