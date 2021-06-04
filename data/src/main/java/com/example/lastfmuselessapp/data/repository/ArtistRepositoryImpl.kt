package com.example.lastfmuselessapp.data.repository

import com.example.lastfmuselessapp.data.local.LocalArtistProvider
import com.example.lastfmuselessapp.data.network.client.ArtistClient
import com.example.lastfmuselessapp.data.network.mapper.ArtistMapper
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.Artist
import com.example.lastfmuselessapp.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val localArtistProvider: LocalArtistProvider,
    private val artistClient: ArtistClient,
    private val artistMapper: ArtistMapper
) : ArtistRepository {

    override suspend fun getTopArtists(numberOfTopArtists: Int): Resource<List<Artist>> {

        return try {
            val artists = artistClient.getTopArtists(10).artists

            Resource.Success(artistMapper.toArtistList(artists))
        } catch (throwable: Throwable) {
            Resource.Error(throwable.toString())
        }
    }
}
