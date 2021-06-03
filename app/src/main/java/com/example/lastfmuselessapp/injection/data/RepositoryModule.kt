package com.example.lastfmuselessapp.injection.data

import com.example.lastfmuselessapp.data.local.LocalArtistProvider
import com.example.lastfmuselessapp.data.local.LocalArtistProviderImpl
import com.example.lastfmuselessapp.data.network.client.ArtistClient
import com.example.lastfmuselessapp.data.network.client.ArtistRetrofitApi
import com.example.lastfmuselessapp.data.network.config.UrlProvider
import com.example.lastfmuselessapp.data.network.config.UrlProviderImpl
import com.example.lastfmuselessapp.data.network.mapper.ArtistMapper
import com.example.lastfmuselessapp.data.network.mapper.ArtistMapperImpl
import com.example.lastfmuselessapp.data.repository.ArtistRepositoryImpl
import com.example.lastfmuselessapp.domain.repository.ArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalArtistProvider(): LocalArtistProvider {
        return LocalArtistProviderImpl()
    }

    @Provides
    @Singleton
    fun provideUrlProvider(): UrlProvider {
        return UrlProviderImpl()
    }

    @Provides
    @Singleton
    fun provideArtistRetrofitApi(urlProvider: UrlProvider): ArtistClient {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(urlProvider.provideBaseUrlLastFmApi())
            .build()
            .create(ArtistRetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArtistMapper(): ArtistMapper {
        return ArtistMapperImpl()
    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        localArtistProvider: LocalArtistProvider,
        artistClient: ArtistClient,
        artistMapper: ArtistMapper
    ): ArtistRepository {
        return ArtistRepositoryImpl(localArtistProvider, artistClient, artistMapper)
    }
}