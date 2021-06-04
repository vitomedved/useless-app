package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.artist.ArtistListWithAttributes
import com.example.lastfmuselessapp.domain.model.artist.Artist

class ArtistMapperImpl : ArtistMapper {
    override fun toArtistList(artistListWithAttributes: ArtistListWithAttributes): List<Artist> {
        return artistListWithAttributes.artistList.map {
            Artist(it.mbid, it.name)
        }
    }
}
