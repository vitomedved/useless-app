package com.example.lastfmuselessapp.data.network.model.artist.search

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("@attr")
    val attribute: SearchArtistAttribute,
    @SerializedName("artistmatches")
    val artistMatches: ArtistMatches,
    @SerializedName("opensearch:Query")
    val searchQuery: SearchQuery,
    @SerializedName("opensearch:itemsPerPage")
    val itemsPerPage: String,
    @SerializedName("opensearch:startIndex")
    val startIndex: String,
    @SerializedName("opensearch:totalResults")
    val totalResults: String
)
