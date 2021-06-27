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

    override suspend fun fetchTopArtistsWorldwide(
        numberOfTopArtists: Int,
        pageNumber: Int?
    ): Resource<List<Artist>> {
        return artistClient.fetchTopArtists(limit = numberOfTopArtists, pageNumber = pageNumber)
    }

    override suspend fun fetchArtist(
        artist: String,
        numberOfResults: Int?,
        pageNumber: Int?
    ): Resource<List<Artist>> {
        return artistClient.fetchArtist(
            artist = artist,
            limit = numberOfResults,
            pageNumber = pageNumber
        )
    }
}
