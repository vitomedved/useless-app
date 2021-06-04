package com.example.lastfmuselessapp.data.network.client

import com.example.lastfmuselessapp.data.network.model.ArtistsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistRetrofitApi : ArtistClient {

    companion object {
        private const val LAST_FM_API_KEY: String = YOUR_API_KEY_STRING_VALUE
    }

    @GET("/2.0/?method=chart.gettopartists&api_key=${LAST_FM_API_KEY}&format=json")
    override suspend fun getTopArtists(
        @Query("limit") limit: Int,
        @Query("page") page: Int?
    ): ArtistsResponse
}
