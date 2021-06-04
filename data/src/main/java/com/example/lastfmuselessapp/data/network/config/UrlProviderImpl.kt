package com.example.lastfmuselessapp.data.network.config

class UrlProviderImpl : UrlProvider {

    companion object {
        private const val LAST_FM_API_BASE_URL = "https://ws.audioscrobbler.com/2.0/"
    }

    override fun provideBaseUrlLastFmApi(): String {
        return LAST_FM_API_BASE_URL
    }
}