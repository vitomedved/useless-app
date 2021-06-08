package com.example.lastfmuselessapp.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastfmuselessapp.domain.repository.ArtistRepository
import com.example.lastfmuselessapp.domain.repository.TrackRepository
import com.example.lastfmuselessapp.mapper.HorizontalCarouselItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val trackRepository: TrackRepository,
    private val horizontalCarouselItemMapper: HorizontalCarouselItemMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val NUMBER_OF_TOP_ITEMS_SHOWN = 10
    }

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = HomeUiState(
                horizontalCarouselItemMapper.fromArtistListResource(
                    artistRepository.getTopArtists(
                        NUMBER_OF_TOP_ITEMS_SHOWN
                    )
                ),
                horizontalCarouselItemMapper.fromTrackListResource(
                    trackRepository.getTopTracksWorldwide(
                        NUMBER_OF_TOP_ITEMS_SHOWN
                    )
                )
            )
        }
    }
}
