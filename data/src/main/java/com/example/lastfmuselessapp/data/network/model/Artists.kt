package com.example.lastfmuselessapp.data.network.model

import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)