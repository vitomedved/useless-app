package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.artist.ArtistListWithAttributes
import com.example.lastfmuselessapp.domain.model.artist.Artist

interface ArtistMapper {

    fun toArtistList(artistListWithAttributes: ArtistListWithAttributes): List<Artist>
}