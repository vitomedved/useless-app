package com.example.lastfmuselessapp.injection.data

import com.example.lastfmuselessapp.data.local.ArtistRepository
import com.example.lastfmuselessapp.data.local.MockArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DataModule {

    @Provides
    fun provideArtistRepository(): ArtistRepository {
        return MockArtistRepository()
    }
}