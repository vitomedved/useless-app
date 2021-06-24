package com.example.lastfmuselessapp.data.network.model.artist.search

import com.google.gson.annotations.SerializedName

data class ArtistMatches(
    @SerializedName("artist")
    val artistList: List<ArtistObject>
)
