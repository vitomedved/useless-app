package com.example.lastfmuselessapp.data.network.client.track

import com.example.lastfmuselessapp.data.network.model.track.localized.TopTracksByCountryResponse
import com.example.lastfmuselessapp.data.network.model.track.worldwide.TopTracksWorldwideResponse
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track

/**
 * This class will be used as a network client for Track data. It will be used to fetch data about Tracks (songs).
 * */
interface TrackClient {

    /**
     * This method will fetch top tracks worldwide.
     *
     * @param limit The number of results to fetch per page. Defaults to 50.
     * @param page  The page number to fetch. Defaults to first page.
     *
     * @return [TopTracksWorldwideResponse] class - a data class mapped from JSON response.
     * */
    suspend fun fetchTopTracksWorldwide(
        limit: Int? = null,
        page: Int? = null
    ): Resource<List<Track>>

    /**
     * Method that will fetch top tracks by specific country.
     *
     * @param country   A country name, as defined by the ISO 3166-1 country names standard.
     * @param location  A metro name, to fetch the charts for (must be within the country specified).
     * @param limit     The number of results to fetch per page. Defaults to 50.
     * @param page      The page number to fetch. Defaults to first page.
     *
     * @return @see [TopTracksByCountryResponse] class - a data class mapped from JSON response.
     * */
    suspend fun fetchTopTracksByCountry(
        country: String,
        location: String? = null,
        limit: Int? = null,
        page: Int? = null
    ): Resource<List<Track>>

    suspend fun fetchTrack(
        track: String,
        artist: String? = null,
        limit: Int? = null,
        page: Int? = null
    ): Resource<List<Track>>
}
