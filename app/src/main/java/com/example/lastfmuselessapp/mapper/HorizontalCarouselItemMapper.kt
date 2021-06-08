package com.example.lastfmuselessapp.mapper

import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.ui.composables.model.HorizontalCarouselItemModel

interface HorizontalCarouselItemMapper {

    fun fromArtistListResource(artistListResource: Resource<List<Artist>>): Resource<List<HorizontalCarouselItemModel>>

    fun fromTrackListResource(trackListResource: Resource<List<Track>>): Resource<List<HorizontalCarouselItemModel>>
}