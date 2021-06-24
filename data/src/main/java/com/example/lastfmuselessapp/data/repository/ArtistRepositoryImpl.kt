package com.example.lastfmuselessapp.data.repository

import com.example.lastfmuselessapp.data.local.LocalArtistProvider
import com.example.lastfmuselessapp.data.network.client.artist.ArtistClient
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val localArtistProvider: LocalArtistProvider,
    private val artistClient: ArtistClient
) : ArtistRepository {

    override suspend fun getTopArtists(numberOfTopArtists: Int): Resource<List<Artist>> {
        return artistClient.fetchTopArtists(10)
    }

    override suspend fun fetchArtist(value: String, numberOfResults: Int?): Resource<List<Artist>> {
        return artistClient.fetchArtist(value, numberOfResults)
    }
}
