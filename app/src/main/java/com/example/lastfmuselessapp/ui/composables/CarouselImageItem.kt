package com.example.lastfmuselessapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun HorizontalCarouselItem(
    imageUrl: String,
    text: String,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit
) {
    Column(modifier = modifier
        .clickable { onItemClicked() }
        .padding(horizontal = 12.dp, vertical = 8.dp)) {
        Image(
            modifier = Modifier
                .weight(0.7f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally),
            painter = rememberCoilPainter(imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(0.3f)
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
                .requiredWidthIn(min = 0.dp, max = 100.dp)
        )
    }
}
