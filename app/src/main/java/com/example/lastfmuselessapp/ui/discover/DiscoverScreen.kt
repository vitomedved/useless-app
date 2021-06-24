package com.example.lastfmuselessapp.ui.discover

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lastfmuselessapp.domain.model.Resource
import com.example.lastfmuselessapp.domain.model.Searchable
import com.example.lastfmuselessapp.domain.model.artist.Artist
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel
import com.example.lastfmuselessapp.domain.model.track.Track
import com.example.lastfmuselessapp.ui.composables.search.*

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
