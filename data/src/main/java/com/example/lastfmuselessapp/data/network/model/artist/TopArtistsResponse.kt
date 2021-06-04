package com.example.lastfmuselessapp.data.network.model.artist

import com.google.gson.annotations.SerializedName

data class TopArtistsResponse(
    @SerializedName("artists")
    val artistListWithAttributes: ArtistListWithAttributes
)
