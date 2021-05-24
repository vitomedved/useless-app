package com.example.lastfmuselessapp.injection.data

import com.example.lastfmuselessapp.data.local.LocalArtistProvider
import com.example.lastfmuselessapp.data.local.MockArtistDataProvider
import com.example.lastfmuselessapp.repository.ArtistRepository
import com.example.lastfmuselessapp.repository.MockArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideLocalArtistProvider(): LocalArtistProvider {
        return MockArtistDataProvider()
    }

    @Provides
    @Singleton
    fun provideArtistRepository(localArtistProvider: LocalArtistProvider): ArtistRepository {
        return MockArtistRepository(localArtistProvider)
    }
}