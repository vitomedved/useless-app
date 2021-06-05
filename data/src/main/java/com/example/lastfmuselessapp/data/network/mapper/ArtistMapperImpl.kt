package com.example.lastfmuselessapp.data.network.mapper

import com.example.lastfmuselessapp.data.network.model.artist.ArtistListWithAttributes
import com.example.lastfmuselessapp.data.network.model.common.Image
import com.example.lastfmuselessapp.domain.model.artist.Artist

class ArtistMapperImpl : ArtistMapper {
    override fun toArtistList(artistListWithAttributes: ArtistListWithAttributes): List<Artist> {
        return artistListWithAttributes.artistList.map {
            Artist(
                it.mbid,
                it.name,
                it.imageList.first { image ->
                    image.size == Image.ImageSize.EXTRA_LARGE.getValue() ||
                            image.size == Image.ImageSize.LARGE.getValue() ||
                            image.size == Image.ImageSize.MEDIUM.getValue()
                }.text
            )
        }
    }
}
