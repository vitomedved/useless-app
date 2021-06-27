package com.example.lastfmuselessapp.provider

import com.example.lastfmuselessapp.R
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel
import com.example.lastfmuselessapp.domain.provider.SearchCategoryProvider

class SearchCategoryProviderImpl(
    private val defaultSearchCategory: SearchCategoryModel.SearchCategory,
    private val availableSearchCategories: List<SearchCategoryModel.SearchCategory>
) : SearchCategoryProvider {

    override fun provideAvailableSearchCategories(): List<SearchCategoryModel> {
        val availableSearchCategoryModels = mutableListOf<SearchCategoryModel>()

        availableSearchCategories.forEach { searchCategory ->
            availableSearchCategoryModels.add(
                SearchCategoryModel(
                    searchCategory = searchCategory,
                    searchCategoryLabel = getLabelForSearchCategory(searchCategory)
                )
            )
        }

        return availableSearchCategoryModels
    }

    private fun getLabelForSearchCategory(searchCategory: SearchCategoryModel.SearchCategory): Int {
        return when (searchCategory) {
            SearchCategoryModel.SearchCategory.UNKNOWN -> R.string.unknown
            SearchCategoryModel.SearchCategory.ARTIST -> R.string.artist
            SearchCategoryModel.SearchCategory.TRACK -> R.string.track
            else -> throw IllegalArgumentException("Search category not implemented")
        }
    }

    override fun provideDefaultSearchCategory(): SearchCategoryModel.SearchCategory {
        return defaultSearchCategory
    }
}
