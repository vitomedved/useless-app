package com.example.lastfmuselessapp.repository

import com.example.lastfmuselessapp.model.OnboardingModel

interface OnboardingRepository {

    fun getOnboardingModel(index: Int?): OnboardingModel
}
