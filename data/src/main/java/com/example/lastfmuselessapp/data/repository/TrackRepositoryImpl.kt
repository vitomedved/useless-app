package com.example.lastfmuselessapp.data.repository

import com.example.lastfmuselessapp.data.network.client.track.TrackClient
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.domain.repository.TrackRepository

class TrackRepositoryImpl(private val trackClient: TrackClient) : TrackRepository {

    override suspend fun getTopTracksWorldwide(numberOfTopTracks: Int): Resource<List<Track>> {
        return trackClient.getTopTracksWorldwide(limit = numberOfTopTracks)
    }

    override suspend fun getTopTracksByCountry(
        country: String,
        numberOfTopTracks: Int
    ): Resource<List<Track>> {
        TODO("Not yet implemented")
    }
}