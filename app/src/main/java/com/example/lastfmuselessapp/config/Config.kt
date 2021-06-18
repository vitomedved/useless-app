package com.example.lastfmuselessapp.config

import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel

object Config {

    val AVAILABLE_SEARCH_CATEGORIES = listOf(
        SearchCategoryModel.SearchCategory.ARTIST,
        SearchCategoryModel.SearchCategory.TRACK
    )

    val DEFAULT_SEARCH_CATEGORY = SearchCategoryModel.SearchCategory.ARTIST
}