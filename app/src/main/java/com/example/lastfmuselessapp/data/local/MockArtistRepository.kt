package com.example.lastfmuselessapp.data.local

class MockArtistRepository : ArtistRepository {

    override fun getArtists(): String {
        return "Katy Perry"
    }
}