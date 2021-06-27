package com.example.lastfmuselessapp.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    resourceName: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // todo resources, maybe refactor this loading thing to look nicer
        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
        Text(
            text = "Loading $resourceName...",
            modifier = Modifier.padding(8.dp)
        )
    }
}
