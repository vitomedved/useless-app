package com.example.lastfmuselessapp.data.network.model.common

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val text: String,
    @SerializedName("size")
    val size: String
) {
    enum class ImageSize(private val value: String) {
        SMALL("small"),
        MEDIUM("medium"),
        LARGE("large"),
        EXTRA_LARGE("extralarge"),
        MEGA("mega");

        fun getValue(): String = value
    }
}
