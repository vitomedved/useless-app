package com.example.lastfmuselessapp.data.network.client.track

import com.example.lastfmuselessapp.data.network.config.Config
import com.example.lastfmuselessapp.data.network.model.track.localized.TopTracksByCountryResponse
import com.example.lastfmuselessapp.data.network.model.track.worldwide.TopTracksWorldwideResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackRetrofitApi {

    @GET("/2.0/?method=geo.gettoptracks&api_key=${Config.LAST_FM_API_KEY}&format=json")
    suspend fun getTopTracksByCountry(
        @Query("country") country: String,
        @Query("location") location: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null
    ): TopTracksByCountryResponse

    @GET("/2.0/?method=chart.gettoptracks&api_key=${Config.LAST_FM_API_KEY}&format=json")
    suspend fun getTopTracksWorldwide(
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null
    ): TopTracksWorldwideResponse
}
