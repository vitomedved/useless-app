package com.example.lastfmuselessapp.model

import androidx.annotation.StringRes
import com.example.lastfmuselessapp.R

sealed class Screen(val route: String, @StringRes val nameResource: Int) {
    object Home : Screen("home", R.string.home)
    object Artist : Screen("artist/{artistId}", R.string.artist) {
        fun getArtistIdArgument(): String = "artistId"
        fun getRouteForArtistId(artistId: String) = "artist/$artistId"
    }
    object Discover : Screen("discover", R.string.discover)
    object Library : Screen("library", R.string.library)
}
