package com.example.lastfmuselessapp.data.network.model.track.search

import com.google.gson.annotations.SerializedName

data class TrackSearchQuery(
    @SerializedName("#text")
    val text: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("startPage")
    val startPage: String
)
