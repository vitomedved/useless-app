package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.Artists
import com.example.lastfmuselessapp.domain.model.Artist

class ArtistMapperImpl : ArtistMapper {
    override fun toArtistList(artists: Artists): List<Artist> {
        return artists.artist.map {
            Artist(it.mbid, it.name)
        }
    }
}
