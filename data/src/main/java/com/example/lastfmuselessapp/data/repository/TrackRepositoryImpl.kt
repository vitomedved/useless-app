package com.example.lastfmuselessapp.data.repository

import com.example.lastfmuselessapp.data.network.client.track.TrackClient
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.domain.repository.TrackRepository

class TrackRepositoryImpl(private val trackClient: TrackClient) : TrackRepository {

    override suspend fun fetchTopTracksWorldwide(
        numberOfTopTracks: Int,
        pageNumber: Int?
    ): Resource<List<Track>> {
        return trackClient.fetchTopTracksWorldwide(limit = numberOfTopTracks, page = pageNumber)
    }

    override suspend fun fetchTopTracksByCountry(
        country: String,
        numberOfTopTracks: Int
    ): Resource<List<Track>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTrack(
        track: String,
        artist: String?,
        limit: Int?,
        pageNumber: Int?
    ): Resource<List<Track>> {
        return trackClient.fetchTrack(track, artist, limit, pageNumber)
    }
}