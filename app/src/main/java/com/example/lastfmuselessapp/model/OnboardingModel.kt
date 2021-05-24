package com.example.lastfmuselessapp.model

data class OnboardingModel(val title: String, val description: String, val index: Int? = null) {
    companion object {
        val EMPTY = OnboardingModel("", "", null)
    }
}