package com.example.lastfmuselessapp.ui.discover

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.Searchable
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel
import com.example.lastfmuselessapp.domain.provider.SearchCategoryProvider
import com.example.lastfmuselessapp.domain.repository.ArtistRepository
import com.example.lastfmuselessapp.domain.repository.TrackRepository
import com.example.lastfmuselessapp.domain.time.TaskScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    searchCategoryProvider: SearchCategoryProvider,
    private val artistRepository: ArtistRepository,
    private val trackRepository: TrackRepository,
    private val taskScheduler: TaskScheduler
) : ViewModel() {

    companion object {
        private const val SEARCH_ITEM_JOB_TAG = "search_item"
        private const val USER_INTERACTION_TIMEOUT_MILLIS = 1000L
    }

    var searchInputValue = mutableStateOf("")
        private set

    var focused = mutableStateOf(false)
        private set

    var availableSearchCategories =
        mutableStateOf(searchCategoryProvider.provideAvailableSearchCategories())
        private set

    var selectedSearchCategory =
        mutableStateOf(searchCategoryProvider.provideDefaultSearchCategory())
        private set

    var searchableItemsList = mutableStateOf<Resource<List<Searchable>>>(Resource.Idle())
        private set

    private var searchItemsJob: Job? = null

    fun onSearchTextCleared() {
        searchInputValue.value = ""
        searchableItemsList.value = Resource.Idle()
    }

    fun onSearchTextChanged(newText: String) {
        searchInputValue.value = newText.replace("\n", "").replace("\r\n", "")

        searchCurrentItemCancellable(delay = USER_INTERACTION_TIMEOUT_MILLIS)
    }

    private fun searchCurrentItemCancellable(delay: Long = 0) {
        cancelSearchItemsJobIfPossible()

        if (searchInputValue.value.isEmpty()) {
            return
        }

        taskScheduler.executeDelayed(SEARCH_ITEM_JOB_TAG, delay) {
            initializeAndStartSearchItemsJob()
        }
    }

    private fun cancelSearchItemsJobIfPossible() {
        searchItemsJob?.cancel()
    }

    private fun initializeAndStartSearchItemsJob() {
        searchItemsJob = viewModelScope.launch {

            searchableItemsList.value = Resource.Loading()

            when (selectedSearchCategory.value) {
                SearchCategoryModel.SearchCategory.ARTIST -> searchArtists()
                SearchCategoryModel.SearchCategory.TRACK -> searchTracks()
                else -> TODO("Unsupported search category selected")
            }
        }

        searchItemsJob?.invokeOnCompletion {
            searchItemsJob = null
        }
    }

    private suspend fun searchArtists() {
        searchableItemsList.value =
            artistRepository.fetchArtist(searchInputValue.value) as Resource<List<Searchable>>
    }

    private suspend fun searchTracks() {
        searchableItemsList.value =
            trackRepository.fetchTrack(searchInputValue.value) as Resource<List<Searchable>>
    }

    fun onCategorySelected(searchCategory: SearchCategoryModel.SearchCategory) {
        selectedSearchCategory.value = searchCategory

        searchCurrentItemCancellable()
    }

    fun onFocusChanged(focused: Boolean) {

        this.focused.value = if (focused) {
            true
        } else {
            searchInputValue.value.isNotEmpty()
        }
    }

    fun onSearchActionClicked() {
        searchCurrentItemCancellable()
    }

    fun onSearchInputBackButtonClicked() {
        searchInputValue.value = ""
        searchableItemsList.value = Resource.Idle()
    }
}
