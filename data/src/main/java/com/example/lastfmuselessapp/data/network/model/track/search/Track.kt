package com.example.lastfmuselessapp.data.network.model.track.search

import com.example.lastfmuselessapp.data.network.model.common.Image
import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("image")
    val imageList: List<Image>,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("url")
    val url: String
)
