package com.example.lastfmuselessapp.injection.data

import com.example.lastfmuselessapp.data.local.LocalArtistProvider
import com.example.lastfmuselessapp.data.local.LocalArtistProviderImpl
import com.example.lastfmuselessapp.data.network.client.artist.ArtistClient
import com.example.lastfmuselessapp.data.network.client.artist.ArtistRetrofitApi
import com.example.lastfmuselessapp.data.network.client.artist.ArtistRetrofitClientImpl
import com.example.lastfmuselessapp.data.network.client.track.TrackClient
import com.example.lastfmuselessapp.data.network.client.track.TrackRetrofitApi
import com.example.lastfmuselessapp.data.network.client.track.TrackRetrofitClientImpl
import com.example.lastfmuselessapp.data.network.config.UrlProvider
import com.example.lastfmuselessapp.data.network.config.UrlProviderImpl
import com.example.lastfmuselessapp.data.network.mapper.ArtistMapper
import com.example.lastfmuselessapp.data.network.mapper.ArtistMapperImpl
import com.example.lastfmuselessapp.data.network.mapper.TrackMapper
import com.example.lastfmuselessapp.data.network.mapper.TrackMapperImpl
import com.example.lastfmuselessapp.data.repository.ArtistRepositoryImpl
import com.example.lastfmuselessapp.data.repository.TrackRepositoryImpl
import com.example.lastfmuselessapp.domain.repository.ArtistRepository
import com.example.lastfmuselessapp.domain.repository.TrackRepository
import com.example.lastfmuselessapp.mapper.HorizontalCarouselItemMapper
import com.example.lastfmuselessapp.mapper.HorizontalCarouselItemMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

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
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideLastFmRetrofitObject(
        gsonConverterFactory: GsonConverterFactory,
        urlProvider: UrlProvider
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(urlProvider.provideBaseUrlLastFmApi())
            .build()
    }

    @Provides
    @Singleton
    fun provideArtistRetrofitApi(lastFmRetrofitObject: Retrofit): ArtistRetrofitApi {
        return lastFmRetrofitObject.create(ArtistRetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArtistMapper(): ArtistMapper {
        return ArtistMapperImpl()
    }

    @Provides
    @Singleton
    fun provideArtistClient(
        artistRetrofitApi: ArtistRetrofitApi,
        artistMapper: ArtistMapper
    ): ArtistClient {
        return ArtistRetrofitClientImpl(artistRetrofitApi, artistMapper)
    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        localArtistProvider: LocalArtistProvider,
        artistClient: ArtistClient
    ): ArtistRepository {
        return ArtistRepositoryImpl(localArtistProvider, artistClient)
    }

    @Provides
    @Singleton
    fun provideTrackRetrofitApi(lastFmRetrofitObject: Retrofit): TrackRetrofitApi {
        return lastFmRetrofitObject.create(TrackRetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrackMapper(): TrackMapper {
        return TrackMapperImpl()
    }

    @Provides
    @Singleton
    fun providesTrackClient(
        trackRetrofitApi: TrackRetrofitApi,
        trackMapper: TrackMapper
    ): TrackClient {
        return TrackRetrofitClientImpl(trackRetrofitApi, trackMapper)
    }

    @Provides
    @Singleton
    fun provideTrackRepository(trackClient: TrackClient): TrackRepository {
        return TrackRepositoryImpl(trackClient)
    }

    @Provides
    @Singleton
    fun provideHorizontalCarouselItemMapper(): HorizontalCarouselItemMapper {
        return HorizontalCarouselItemMapperImpl()
    }
}
