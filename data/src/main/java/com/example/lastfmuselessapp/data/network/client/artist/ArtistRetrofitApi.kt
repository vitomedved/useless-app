package com.example.lastfmuselessapp.data.network.client.artist

import com.example.lastfmuselessapp.data.network.config.Config
import com.example.lastfmuselessapp.data.network.model.artist.TopArtistsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ArtistRetrofitApi {

    @GET("/2.0/?method=chart.gettopartists&api_key=${Config.LAST_FM_API_KEY}&format=json")
    suspend fun getTopArtists(
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null
    ): TopArtistsResponse
}
