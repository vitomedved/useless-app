package com.example.lastfmuselessapp.data.network.model.artist

import com.example.lastfmuselessapp.data.network.model.common.TopItemAttribute
import com.google.gson.annotations.SerializedName

data class ArtistListWithAttributes(
    @SerializedName("@attr")
    val attribute: TopItemAttribute,
    @SerializedName("artist")
    val artistList: List<TopArtistObject>
)
