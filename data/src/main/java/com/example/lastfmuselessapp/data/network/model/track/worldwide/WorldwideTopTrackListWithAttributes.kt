package com.example.lastfmuselessapp.data.network.model.track.worldwide

import com.example.lastfmuselessapp.data.network.model.common.Attribute
import com.google.gson.annotations.SerializedName

data class WorldwideTopTrackListWithAttributes(
    @SerializedName("@attr")
    val attr: Attribute,
    @SerializedName("track")
    val trackList: List<TopTrackWorldwide>
)
