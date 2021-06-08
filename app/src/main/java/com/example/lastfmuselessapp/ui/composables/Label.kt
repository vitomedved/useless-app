package com.example.lastfmuselessapp.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Label(text: String) {
    Text(
        text = text,
        // TODO modify MaterialTheme typography
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(
            // TODO extract values to resources
            top = 12.dp,
            start = 12.dp,
            end = 12.dp
        )
    )
}