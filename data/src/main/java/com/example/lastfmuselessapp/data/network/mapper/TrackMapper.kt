package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.track.localized.TopTracksByCountryResponse
import com.example.lastfmuselessapp.data.network.model.track.worldwide.TopTracksWorldwideResponse
import com.example.lastfmuselessapp.domain.model.track.Track

/**
 * This class is responsible for mapping fetched JSON response to domain model class.
 * */
interface TrackMapper {

    fun toTrackList(worldwideTopTracks: TopTracksWorldwideResponse): List<Track>

    fun toTrackList(topTracksByCountry: TopTracksByCountryResponse): List<Track>
}