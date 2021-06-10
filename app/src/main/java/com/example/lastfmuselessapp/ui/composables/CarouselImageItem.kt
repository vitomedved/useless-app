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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun HorizontalCarouselItem(
    modifier: Modifier = Modifier,
    id: String,
    imageUrl: String,
    label: String,
    onItemClicked: (String) -> Unit
) {
    Column(modifier = modifier
        .clickable { onItemClicked(id) }
        // TODO resources
        .padding(horizontal = 12.dp, vertical = 8.dp)) {
        Image(
            // TODO check if this can and should be in res
            modifier = Modifier
                .weight(0.7f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally),
            painter = rememberCoilPainter(imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            // todo res? all other resources same comment
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

@Preview("Carousel item")
@Composable
fun PreviewItem() {
    HorizontalCarouselItem(id = "id", imageUrl = "imageUrl", label = "label") {

    }
}
