package com.example.lastfmuselessapp.domain.model.track

import com.example.lastfmuselessapp.domain.model.Searchable

data class Track(
    val id: String,
    val name: String,
    val artistName: String,
    val imageUrl: String
) : Searchable()
