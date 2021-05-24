package com.example.lastfmuselessapp.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(
    onFinishOnboardingClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(onClick = { onFinishOnboardingClicked() }) {
            Text(text = "Go home")
        }
    }
}

@Composable
fun TextViewWithShapeBackground() {
    Text(text = "test")

}

@Preview
@Composable
fun Preview() {
    TextViewWithShapeBackground()
}
