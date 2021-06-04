package com.example.lastfmuselessapp.data.network.model.track.worldwide

import com.google.gson.annotations.SerializedName

data class TopTracksWorldwideResponse(
    @SerializedName("tracks")
    val worldwideTopTrackListWithAttributes: WorldwideTopTrackListWithAttributes
)
