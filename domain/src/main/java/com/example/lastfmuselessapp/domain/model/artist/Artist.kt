package com.example.lastfmuselessapp.domain.model.artist

import com.example.lastfmuselessapp.domain.model.Searchable

data class Artist(val id: String, val name: String, val imageUrl: String) : Searchable()
