package com.example.lastfmuselessapp.data.network.model.track.worldwide

import com.example.lastfmuselessapp.data.network.model.common.TopItemAttribute
import com.google.gson.annotations.SerializedName

data class WorldwideTopTrackListWithAttributes(
    @SerializedName("@attr")
    val attr: TopItemAttribute,
    @SerializedName("track")
    val trackList: List<TopTrackWorldwide>
)
