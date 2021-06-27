package com.example.lastfmuselessapp.domain.repository

import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track

interface TrackRepository {

    suspend fun fetchTopTracksWorldwide(numberOfTopTracks: Int, pageNumber: Int? = null): Resource<List<Track>>

    suspend fun fetchTopTracksByCountry(
        country: String,
        numberOfTopTracks: Int
    ): Resource<List<Track>>

    suspend fun fetchTrack(
        track: String,
        artist: String? = null,
        limit: Int? = null,
        pageNumber: Int? = null
    ): Resource<List<Track>>
}
