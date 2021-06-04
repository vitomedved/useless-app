package com.example.lastfmuselessapp.data.network.client

import com.example.lastfmuselessapp.data.network.model.ArtistsResponse

interface ArtistClient {

    suspend fun getTopArtists(limit: Int, page: Int? = null): ArtistsResponse
}
