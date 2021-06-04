package com.example.lastfmuselessapp.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.example.lastfmuselessapp.R

@Composable
fun OnboardingScreen(
    onFinishOnboardingClicked: () -> Unit
) {
    val lottieAnimationSpec = LottieAnimationSpec.RawRes(R.raw.artist_animation)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        LottieAnimation(
            spec = lottieAnimationSpec,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Button(onClick = { onFinishOnboardingClicked() }) {
            Text(text = "Go home")
        }


    }
}
