package com.example.lastfmuselessapp.data.network.model.artist

import com.example.lastfmuselessapp.data.network.model.common.Image
import com.google.gson.annotations.SerializedName

data class TopArtistObject(
    @SerializedName("image")
    val imageList: List<Image>,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playcount")
    val playcount: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("url")
    val url: String
)
