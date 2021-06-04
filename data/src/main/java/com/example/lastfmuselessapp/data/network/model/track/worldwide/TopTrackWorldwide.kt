package com.example.lastfmuselessapp.data.network.model.track.worldwide

import com.example.lastfmuselessapp.data.network.model.common.Image
import com.example.lastfmuselessapp.data.network.model.common.Streamable
import com.example.lastfmuselessapp.data.network.model.track.TrackArtist
import com.google.gson.annotations.SerializedName

data class TopTrackWorldwide(
    @SerializedName("artist")
    val artist: TrackArtist,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("image")
    val imageList: List<Image>,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playcount")
    val playCount: String,
    @SerializedName("streamable")
    val streamable: Streamable,
    @SerializedName("url")
    val url: String
)
