package com.example.lastfmuselessapp.data.network.client.artist

import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.artist.Artist

/**
 * This class will be used as a Artist data network client. The class is responsible for fetching data about Artists from the network.
 * */
interface ArtistClient {

    /**
     * This method will fetch top artists worldwide.
     *
     * @param limit The number of results to fetch per page. Defaults to 50.
     * @param pageNumber The page number to fetch. Defaults to first page.
     *
     * @return Resource object of list of [Artist] items.
     * */
    suspend fun fetchTopArtists(limit: Int? = null, pageNumber: Int? = null): Resource<List<Artist>>

    /**
     * This method will fetch all matching artists for a given value.
     *
     * @param artist Value by which artists will be searched.
     * @param limit The number of results to fetch per page. Defaults to 30.
     * @param pageNumber The page number to fetch. Defaults to first page.
     * */
    suspend fun fetchArtist(
        artist: String,
        limit: Int? = null,
        pageNumber: Int? = null
    ): Resource<List<Artist>>
}
