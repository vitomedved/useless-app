package com.example.lastfmuselessapp.data.network.model.artist

import com.example.lastfmuselessapp.data.network.model.common.Attribute
import com.google.gson.annotations.SerializedName

data class ArtistListWithAttributes(
    @SerializedName("@attr")
    val attribute: Attribute,
    @SerializedName("artist")
    val artistList: List<ArtistObject>
)
