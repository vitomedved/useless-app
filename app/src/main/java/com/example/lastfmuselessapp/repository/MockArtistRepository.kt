package com.example.lastfmuselessapp.repository

import com.example.lastfmuselessapp.data.local.LocalArtistProvider

class MockArtistRepository(private val localArtistProvider: LocalArtistProvider) : ArtistRepository {

    override fun getArtists(): String {
        return localArtistProvider.getArtistName()
    }
}