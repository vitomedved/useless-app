package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.Artists
import com.example.lastfmuselessapp.domain.model.Artist

interface ArtistMapper {

    fun toArtistList(artists: Artists): List<Artist>
}