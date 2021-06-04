package com.example.lastfmuselessapp.data.network.model.track.localized

import com.google.gson.annotations.SerializedName

data class TopTracksAttribute(
    @SerializedName("country")
    val country: String,
    @SerializedName("page")
    val page: String,
    @SerializedName("perPage")
    val perPage: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("totalPages")
    val totalPages: String
)
