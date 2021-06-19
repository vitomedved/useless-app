package com.example.lastfmuselessapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.domain.model.Resource

@Composable
fun <T> HorizontalScrollingCarousel(
    modifier: Modifier = Modifier,
    itemList: Resource<List<T>>,
    loading: @Composable () -> Unit,
    error: @Composable (String?) -> Unit,
    item: @Composable (T) -> Unit
) {
    when (itemList) {
        is Resource.Loading -> loading()
        is Resource.Error -> error(itemList.message)
        is Resource.Success -> {
            itemList.data?.let { nonNullItemList ->

                LazyRow(
                    modifier = modifier
                        .background(color = MaterialTheme.colors.background)
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(nonNullItemList) { item ->
                        item(item)
                    }
                }
            }
        }
        else -> TODO("Not implemented state.")
    }
}
