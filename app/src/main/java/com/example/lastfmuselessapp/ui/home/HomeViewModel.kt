package com.example.lastfmuselessapp.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.domain.repository.ArtistRepository
import com.example.lastfmuselessapp.domain.repository.TrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val trackRepository: TrackRepository
) : ViewModel() {

    companion object {
        private const val NUMBER_OF_TOP_ITEMS_SHOWN = 10
    }

    var topTracksWorldwide: MutableState<Resource<List<Artist>>> = mutableStateOf(Resource.Loading())
        private set

    var topArtistsWorldwide: MutableState<Resource<List<Track>>> = mutableStateOf(Resource.Loading())
        private set

    init {
        viewModelScope.launch {
            topTracksWorldwide.value = artistRepository.fetchTopArtistsWorldwide(NUMBER_OF_TOP_ITEMS_SHOWN)
            topArtistsWorldwide.value = trackRepository.fetchTopTracksWorldwide(NUMBER_OF_TOP_ITEMS_SHOWN)
        }
    }
}
