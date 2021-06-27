package com.example.lastfmuselessapp.data.network.model.track.search

import com.google.gson.annotations.SerializedName

data class SearchTrackResultContent(
    @SerializedName("@attr")
    val attribute: EmptyAttribute,
    @SerializedName("opensearch:Query")
    val searchQuery: TrackSearchQuery,
    @SerializedName("opensearch:itemsPerPage")
    val itemsPerPage: String,
    @SerializedName("opensearch:startIndex")
    val startIndex: String,
    @SerializedName("opensearch:totalResults")
    val totalResults: String,
    @SerializedName("trackmatches")
    val trackMatches: TrackMatches
)
