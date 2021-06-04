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
    fun provideLocalArtistProvider(): LocalArtistProvider = LocalArtistProviderImpl()

    @Provides
    @Singleton
    fun provideUrlProvider(): UrlProvider = UrlProviderImpl()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideLastFmRetrofitObject(
        gsonConverterFactory: GsonConverterFactory,
        urlProvider: UrlProvider
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(urlProvider.provideBaseUrlLastFmApi())
        .build()

    @Provides
    @Singleton
    fun provideArtistRetrofitApi(lastFmRetrofitObject: Retrofit): ArtistRetrofitApi =
        lastFmRetrofitObject.create(ArtistRetrofitApi::class.java)

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
    ): ArtistClient = ArtistRetrofitClientImpl(artistRetrofitApi, artistMapper)

    @Provides
    @Singleton
    fun provideArtistRepository(
        localArtistProvider: LocalArtistProvider,
        artistClient: ArtistClient
    ): ArtistRepository = ArtistRepositoryImpl(localArtistProvider, artistClient)

    @Provides
    @Singleton
    fun provideTrackRetrofitApi(lastFmRetrofitObject: Retrofit): TrackRetrofitApi =
        lastFmRetrofitObject.create(TrackRetrofitApi::class.java)

    @Provides
    @Singleton
    fun provideTrackMapper(): TrackMapper = TrackMapperImpl()

    @Provides
    @Singleton
    fun providesTrackClient(
        trackRetrofitApi: TrackRetrofitApi,
        trackMapper: TrackMapper
    ): TrackClient = TrackRetrofitClientImpl(trackRetrofitApi, trackMapper)

    @Provides
    @Singleton
    fun provideTrackRepository(trackClient: TrackClient): TrackRepository =
        TrackRepositoryImpl(trackClient)
}
