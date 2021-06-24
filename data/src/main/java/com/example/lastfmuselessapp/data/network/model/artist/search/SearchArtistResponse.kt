package com.example.lastfmuselessapp.data.network.model.artist.search

import com.google.gson.annotations.SerializedName

data class SearchArtistResponse(
    @SerializedName("results")
    val results: Results
)
