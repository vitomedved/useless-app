package com.example.lastfmuselessapp.ui.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.Searchable
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.ui.composables.search.SearchTextField
import com.example.lastfmuselessapp.ui.composables.search.SearchTextFieldBackButton
import com.example.lastfmuselessapp.ui.composables.search.SearchTextFieldBackground
import com.example.lastfmuselessapp.ui.composables.search.SearchTextFieldToggleableClearTextAndPhotoActionIcon
import java.lang.RuntimeException

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DiscoverScreen(
    searchText: String,
    focused: Boolean,
    availableSearchCategories: List<SearchCategoryModel>,
    selectedSearchCategory: SearchCategoryModel.SearchCategory,
    searchableItemsList: Resource<List<Searchable>>,
    onSearchTextChanged: (String) -> Unit,
    onSearchTextCleared: () -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onSearchClicked: () -> Unit,
    onCategorySelected: (SearchCategoryModel.SearchCategory) -> Unit,
    onSearchInputBackButtonClicked: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextFieldBackground(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.08f)
        ) {
            SearchTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                text = searchText,
                hintText = "Search",
                focused = focused,
                onTextChanged = onSearchTextChanged,
                onFocusChanged = onFocusChanged,
                onSearchClicked = onSearchClicked,
                leadingIcon = {
                    SearchTextFieldBackButton(
                        textFieldFocused = focused,
                        onBackButtonClicked = onSearchInputBackButtonClicked
                    )
                },
                trailingIcon = {
                    SearchTextFieldToggleableClearTextAndPhotoActionIcon(
                        textFieldFocused = focused,
                        textEmpty = searchText.isEmpty(),
                        onTextCleared = onSearchTextCleared,
                        onPhotoActionClicked = {
                            // TODO
                        }
                    )
                }
            )
        }

        AnimatedSearchCategoryItemsRow(
            searchCategoryItems = availableSearchCategories,
            selectedSearchCategory = selectedSearchCategory,
            visible = focused && searchText.isNotEmpty(),
            onCategorySelected = onCategorySelected
        )


        when (searchableItemsList) {
            is Resource.Error -> {
                // TODO show error?
                println("Error: ${searchableItemsList.message}")
            }
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                // TODO move to separate Composable
                LazyColumn {
                    items(searchableItemsList.data as List<Searchable>) { searchableItem ->
                        when (searchableItem) {
                            is Artist -> {
                                Text("Artist: ${searchableItem.name}")
                            }
                            is Track -> {
                                Text("Track: ${searchableItem.name}")
                            }
                            else -> throw RuntimeException("Searchable item not implemented")
                        }
                    }

                }
            }
            else -> {
                // NO-OP
            }
        }
    }
}

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
