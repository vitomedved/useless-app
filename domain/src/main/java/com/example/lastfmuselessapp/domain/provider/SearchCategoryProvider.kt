package com.example.lastfmuselessapp.domain.provider

import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel

interface SearchCategoryProvider {

    fun provideAvailableSearchCategories(): List<SearchCategoryModel>

    fun provideDefaultSearchCategory(): SearchCategoryModel.SearchCategory
}
