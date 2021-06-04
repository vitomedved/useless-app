package com.example.lastfmuselessapp.data.network.model.track

import com.google.gson.annotations.SerializedName

data class TrackArtist(
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)