package com.example.lastfmuselessapp.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OnboardingScreen(
    navController: NavController,
    onboardingUiState: OnboardingUiState,
    onNextButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        if (onboardingUiState.isEmpty) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.CenterHorizontally),
                text = onboardingUiState.onboardingModel.description + ", " + onboardingUiState.onboardingModel.index
            )
        }

        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(12.dp),
            onClick = { onNextButtonClicked() }) {
            Text(text = "Go to next fragment")
        }
    }
}
