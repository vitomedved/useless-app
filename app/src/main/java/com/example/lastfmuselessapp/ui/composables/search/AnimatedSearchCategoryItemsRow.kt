package com.example.lastfmuselessapp.ui.composables.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedSearchCategoryItemsRow(
    modifier: Modifier = Modifier,
    searchCategoryItems: List<SearchCategoryModel>,
    selectedSearchCategory: SearchCategoryModel.SearchCategory,
    visible: Boolean,
    onCategorySelected: (SearchCategoryModel.SearchCategory) -> Unit
) {
    Box(modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            SearchCategoryTabs(
                searchCategoryItems = searchCategoryItems,
                selectedSearchCategory = selectedSearchCategory,
                onCategorySelected = onCategorySelected,
            )
        }
    }
}

private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Composable
fun SearchCategoryTabs(
    modifier: Modifier = Modifier,
    searchCategoryItems: List<SearchCategoryModel>,
    selectedSearchCategory: SearchCategoryModel.SearchCategory,
    onCategorySelected: (SearchCategoryModel.SearchCategory) -> Unit
) {
    val selectedIndex =
        searchCategoryItems.indexOfFirst { it.searchCategory == selectedSearchCategory }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        divider = {},
        edgePadding = 24.dp, // TODO ?
        indicator = emptyTabIndicator,
        modifier = modifier
    ) {
        searchCategoryItems.forEachIndexed { index, category ->

            Surface(modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onCategorySelected(category.searchCategory) }
                )
                .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                CategoryChipItem(
                    text = stringResource(id = category.searchCategoryLabel),
                    selected = index == selectedIndex,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun CategoryChipItem(text: String, selected: Boolean, modifier: Modifier) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary.copy(0.85f)
            else -> Color.Transparent
        },
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(percent = 50), // TODO extract all these hardcoded values to Shapes.kt, etc.
        border = BorderStroke(
            width = when {
                selected -> 2.dp
                else -> 1.dp
            },
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.onSurface
            }
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            textAlign = TextAlign.Center
        )
    }
}
