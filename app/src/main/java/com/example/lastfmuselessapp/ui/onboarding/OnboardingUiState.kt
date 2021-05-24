package com.example.lastfmuselessapp.ui.onboarding

import com.example.lastfmuselessapp.model.OnboardingModel

class OnboardingUiState(
    val onboardingModel: OnboardingModel = OnboardingModel.EMPTY,
    onButtonClicked: () -> Unit = {},
    val isEmpty: Boolean = true
)