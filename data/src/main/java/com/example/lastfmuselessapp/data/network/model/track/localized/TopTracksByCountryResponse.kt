package com.example.lastfmuselessapp.data.network.model.track.localized

import com.google.gson.annotations.SerializedName

data class TopTracksByCountryResponse(
    @SerializedName("tracks")
    val trackListWithAttributes: TrackListWithAttributes
)
