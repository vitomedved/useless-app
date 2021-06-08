package com.example.lastfmuselessapp.mapper

import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.ui.composables.model.HorizontalCarouselItemModel

class HorizontalCarouselItemMapperImpl : HorizontalCarouselItemMapper {

    override fun fromArtistListResource(artistListResource: Resource<List<Artist>>): Resource<List<HorizontalCarouselItemModel>> {
        return artistListResource.map { artistListResourceToHorizontalCarouselItemList(artistList = it) }
    }

    private fun artistListResourceToHorizontalCarouselItemList(artistList: List<Artist>?): List<HorizontalCarouselItemModel>? {
        return artistList?.map { toHorizontalCarouselItem(it) }
    }

    private fun toHorizontalCarouselItem(artist: Artist): HorizontalCarouselItemModel {
        return HorizontalCarouselItemModel(
            id = artist.id,
            imageUrl = artist.imageUrl,
            label = artist.name
        )
    }

    override fun fromTrackListResource(trackListResource: Resource<List<Track>>): Resource<List<HorizontalCarouselItemModel>> {
        return trackListResource.map { trackListResourceToHorizontalCarouselItemList(it) }
    }

    private fun trackListResourceToHorizontalCarouselItemList(artistList: List<Track>?): List<HorizontalCarouselItemModel>? {
        return artistList?.map { toHorizontalCarouselItem(it) }
    }

    private fun toHorizontalCarouselItem(track: Track): HorizontalCarouselItemModel {
        return HorizontalCarouselItemModel(
            id = track.id,
            imageUrl = track.imageUrl,
            label = track.name
        )
    }
}
