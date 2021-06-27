package com.example.lastfmuselessapp.data.network.model.track.search

import com.google.gson.annotations.SerializedName

data class SearchTrackResponse(
    @SerializedName("results")
    val searchTrackResultContent: SearchTrackResultContent
)
