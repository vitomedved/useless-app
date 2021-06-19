package com.example.lastfmuselessapp.ui.discover

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lastfmuselessapp.domain.model.Searchable
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel
import com.example.lastfmuselessapp.domain.provider.SearchCategoryProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    searchCategoryProvider: SearchCategoryProvider,
) : ViewModel() {

    var searchState = mutableStateOf("")
        private set

    var focused = mutableStateOf(false)
        private set

    var availableSearchCategories =
        mutableStateOf(searchCategoryProvider.provideAvailableSearchCategories())
        private set

    var selectedSearchCategory =
        mutableStateOf(searchCategoryProvider.provideDefaultSearchCategory())
        private set

    var searchableItemsList = mutableStateOf<List<Searchable>>(listOf())
        private set

    fun onSearchTextCleared() {
        searchState.value = ""
        searchableItemsList.value = listOf()
    }

    fun onSearchTextChanged(newText: String) {
        searchState.value = newText.replace("\n", "").replace("\r\n", "")
        // TODO search for item based on selected searchCategory and searchText

        // TODO add the search possibility after every letter as some kind of settings option "Dynamic search"
    }

    fun onSelectedSearchCategoryChanged(searchCategory: SearchCategoryModel.SearchCategory) {
        selectedSearchCategory.value = searchCategory
        // TODO search for new item category if text is not empty
    }

    fun onFocusChanged(focused: Boolean) {
        this.focused.value = focused
        if (!focused) {
            onSearchTextCleared()
        }
    }
}