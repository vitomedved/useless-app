package com.example.lastfmuselessapp.domain.repository

import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track

interface TrackRepository {

    suspend fun getTopTracksWorldwide(numberOfTopTracks: Int): Resource<List<Track>>

    suspend fun getTopTracksByCountry(
        country: String,
        numberOfTopTracks: Int
    ): Resource<List<Track>>
}
