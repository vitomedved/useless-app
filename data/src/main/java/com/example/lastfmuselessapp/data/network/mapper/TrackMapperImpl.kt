package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.common.Image
import com.example.lastfmuselessapp.data.network.model.track.localized.TopTracksByCountryResponse
import com.example.lastfmuselessapp.data.network.model.track.search.SearchTrackResponse
import com.example.lastfmuselessapp.data.network.model.track.worldwide.TopTracksWorldwideResponse
import com.example.lastfmuselessapp.domain.model.track.Track

class TrackMapperImpl : TrackMapper {

    override fun toTrackList(worldwideTopTracks: TopTracksWorldwideResponse): List<Track> {
        return worldwideTopTracks.worldwideTopTrackListWithAttributes.trackList.map {
            Track(
                it.mbid,
                it.name,
                it.artist.name,
                it.imageList.first { image ->
                    image.size == Image.ImageSize.EXTRA_LARGE.getValue() ||
                            image.size == Image.ImageSize.LARGE.getValue() ||
                            image.size == Image.ImageSize.MEDIUM.getValue()
                }.text
            )
        }
    }

    override fun toTrackList(topTracksByCountry: TopTracksByCountryResponse): List<Track> {
        TODO("Not yet implemented")
    }

    override fun toTrackList(searchTrackResponse: SearchTrackResponse): List<Track> {
        return searchTrackResponse.searchTrackResultContent.trackMatches.trackList.map {
            Track(
                it.mbid,
                it.name,
                it.artist,
                it.imageList.first { image ->
                    image.size == Image.ImageSize.EXTRA_LARGE.getValue() ||
                            image.size == Image.ImageSize.LARGE.getValue() ||
                            image.size == Image.ImageSize.MEDIUM.getValue()
                }.text
            )
        }
    }
}
