package com.example.lastfmuselessapp.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(homeUiState: HomeUiState) {
    Column {
        if (homeUiState.isLoading) {
            Text(text = "Loading screen...")
        } else {
            Text(text = "Welcome back!")
        }
    }
}
