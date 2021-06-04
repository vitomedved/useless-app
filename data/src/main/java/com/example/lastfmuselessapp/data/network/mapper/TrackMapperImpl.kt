package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.track.localized.TopTracksByCountryResponse
import com.example.lastfmuselessapp.data.network.model.track.worldwide.TopTracksWorldwideResponse
import com.example.lastfmuselessapp.domain.model.track.Track

class TrackMapperImpl : TrackMapper {

    override fun toTrackList(worldwideTopTracks: TopTracksWorldwideResponse): List<Track> {
        return worldwideTopTracks.worldwideTopTrackListWithAttributes.trackList.map {
            Track(
                it.mbid, it.name, it.artist.name
            )
        }
    }

    override fun toTrackList(topTracksByCountry: TopTracksByCountryResponse): List<Track> {
        TODO("Not yet implemented")
    }
}