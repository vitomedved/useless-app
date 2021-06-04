package com.example.lastfmuselessapp.data.network.client.artist

import com.example.lastfmuselessapp.data.network.mapper.ArtistMapper
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.artist.Artist

/**
 * Concrete Retrofit implementation of [ArtistClient] which will fetch data from Last.fm API.
 * */
class ArtistRetrofitClientImpl(
    private val artistRetrofitApi: ArtistRetrofitApi,
    private val artistMapper: ArtistMapper
) : ArtistClient {

    override suspend fun getTopArtists(limit: Int?, page: Int?): Resource<List<Artist>> {
        return try {
            Resource.Success(
                artistMapper.toArtistList(
                    artistRetrofitApi.getTopArtists(
                        limit = limit,
                        page = page
                    ).artistListWithAttributes
                )
            )
        } catch (throwable: Throwable) {
            Resource.Error("Unable to fetch artist data: $throwable")
        }
    }
}
