package com.example.lastfmuselessapp.domain.repository

import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.Resource

interface ArtistRepository {

    suspend fun fetchTopArtistsWorldwide(
        numberOfTopArtists: Int,
        pageNumber: Int? = null
    ): Resource<List<Artist>>

    suspend fun fetchArtist(
        artist: String,
        numberOfResults: Int? = null,
        pageNumber: Int? = null
    ): Resource<List<Artist>>
}
