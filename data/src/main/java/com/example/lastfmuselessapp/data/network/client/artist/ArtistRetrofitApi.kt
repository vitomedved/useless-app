package com.example.lastfmuselessapp.data.network.client.artist

import com.example.lastfmuselessapp.data.network.config.Config
import com.example.lastfmuselessapp.data.network.model.artist.TopArtistsResponse
import com.example.lastfmuselessapp.data.network.model.artist.search.SearchArtistResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ArtistRetrofitApi {

    @GET("/2.0/?method=chart.gettopartists&api_key=${Config.LAST_FM_API_KEY}&format=json")
    suspend fun fetchTopArtists(
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null
    ): TopArtistsResponse

    @GET(" /2.0/?method=artist.search&api_key=${Config.LAST_FM_API_KEY}&format=json ")
    suspend fun fetchArtists(
        @Query("artist") value: String,
        @Query("limit") limit: Int?,
        @Query("page") pageNumber: Int?
    ): SearchArtistResponse
}
