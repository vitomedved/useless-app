package com.example.lastfmuselessapp.ui.onboarding

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.lastfmuselessapp.model.OnboardingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(/*onboardingRepository: OnboardingRepository, */
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var onboardingModelIndex: Int? = savedStateHandle.get<Int>("index")

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState

    init {
        val onboardingModel = OnboardingModel(
            "TitleX",
            "Description",
            onboardingModelIndex
        )//onboardingRepository.getOnboardingModel(onboardingModelIndex)
        _uiState.value = OnboardingUiState(
            onboardingModel = onboardingModel,
            isEmpty = if (onboardingModelIndex == null) true else onboardingModelIndex == 0
        )
    }
}