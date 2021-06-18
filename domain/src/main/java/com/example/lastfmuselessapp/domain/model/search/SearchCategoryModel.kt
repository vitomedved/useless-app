package com.example.lastfmuselessapp.domain.model.search

import androidx.annotation.StringRes


data class SearchCategoryModel(
    val searchCategory: SearchCategory,
    @StringRes val searchCategoryLabel: Int
) {
    companion object {
        val EMPTY = SearchCategoryModel(SearchCategory.UNKNOWN, Int.MAX_VALUE)
    }

    enum class SearchCategory {
        UNKNOWN,
        ARTIST,
        TRACK
    }
}
