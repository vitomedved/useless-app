package com.example.lastfmuselessapp.data.network.model.common

import com.google.gson.annotations.SerializedName

data class Attribute(
    @SerializedName("page")
    val page: String,
    @SerializedName("perPage")
    val perPage: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("totalPages")
    val totalPages: String
)
