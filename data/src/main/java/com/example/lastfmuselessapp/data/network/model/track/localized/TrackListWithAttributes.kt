package com.example.lastfmuselessapp.data.network.model.track.localized

import com.google.gson.annotations.SerializedName

data class TrackListWithAttributes(
    @SerializedName("@attr")
    val topTracksAttribute: TopTracksAttribute,
    @SerializedName("track")
    val trackList: List<TrackObject>
)
