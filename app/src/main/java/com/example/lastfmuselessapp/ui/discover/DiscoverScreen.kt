package com.example.lastfmuselessapp.ui.discover

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.Searchable
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.ui.composables.search.*
import com.google.accompanist.coil.rememberCoilPainter

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
    onSearchInputBackButtonClicked: () -> Unit,
    innerPaddingValues: PaddingValues
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ) {
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

                val state = rememberLazyListState()

                LazyColumn(state = state) {
                    items(searchableItemsList.data as List<Searchable>) { item ->

                        ClickableListItemWithImage(
                            title = when (item) {
                                is Artist -> item.name
                                is Track -> item.name
                                else -> ""
                            },
                            subtitle = when (item) {
                                is Artist -> ""
                                is Track -> item.artistName
                                else -> ""
                            },
                            imageUrl = when (item) {
                                is Artist -> item.imageUrl
                                is Track -> item.imageUrl
                                else -> ""
                            },
                            modifier = Modifier.padding(8.dp),
                            onItemClicked = {
                                // TODO
                                println("Clicked on $item")
                            }
                        )
                    }

                }
            }
            else -> {
                // NO-OP
            }
        }
    }
}

@Composable
fun ClickableListItemWithImage(
    title: String,
    subtitle: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked()
            }
    ) {
        Row(modifier = modifier) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                Image(
                    painter = rememberCoilPainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(title, fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = subtitle)
                }
            }
        }
    }
}
