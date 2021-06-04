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
     * @param page The page number to fetch. Defaults to first page.
     *
     * @return Resource object of list of [Artist] items.
     * */
    suspend fun getTopArtists(limit: Int?, page: Int? = null): Resource<List<Artist>>
}
