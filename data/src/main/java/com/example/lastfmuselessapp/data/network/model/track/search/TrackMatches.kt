package com.example.lastfmuselessapp.data.network.model.track.search

import com.google.gson.annotations.SerializedName

data class TrackMatches(
    @SerializedName("track")
    val trackList: List<Track>
)
