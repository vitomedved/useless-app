package com.example.lastfmuselessapp.data.network.client.track

import com.example.lastfmuselessapp.data.network.mapper.TrackMapper
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.track.Track

/**
 * Concrete Retrofit implementation of [TrackClient] which will fetch data from Last.fm API.
 * */
class TrackRetrofitClientImpl(
    private val trackRetrofitApi: TrackRetrofitApi,
    private val trackMapper: TrackMapper
) : TrackClient {

    override suspend fun fetchTopTracksWorldwide(limit: Int?, page: Int?): Resource<List<Track>> {
        return try {
            Resource.Success(
                trackMapper.toTrackList(
                    trackRetrofitApi.fetchTopTracksWorldwide(
                        limit = limit,
                        page = page
                    )
                )
            )
        } catch (throwable: Throwable) {
            Resource.Error("Unable to fetch top tracks data: $throwable")
        }
    }

    override suspend fun fetchTopTracksByCountry(
        country: String,
        location: String?,
        limit: Int?,
        page: Int?
    ): Resource<List<Track>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTrack(
        track: String,
        artist: String?,
        limit: Int?,
        page: Int?
    ): Resource<List<Track>> {
        return try {
            Resource.Success(
                trackMapper.toTrackList(
                    trackRetrofitApi.fetchTrack(
                        track = track,
                        artist = artist,
                        limit = limit,
                        page = page
                    )
                )
            )
        } catch (throwable: Throwable) {
            Resource.Error("Unable to fetch track data: $throwable")
        }
    }
}
