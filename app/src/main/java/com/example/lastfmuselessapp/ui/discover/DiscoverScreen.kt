package com.example.lastfmuselessapp.ui.discover

import androidx.compose.animation.*
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.R
import com.example.lastfmuselessapp.domain.model.Searchable
import com.example.lastfmuselessapp.domain.model.search.SearchCategoryModel
import com.example.lastfmuselessapp.ui.composables.search.SearchTextField
import com.example.lastfmuselessapp.ui.composables.search.SearchTextFieldBackButton
import com.example.lastfmuselessapp.ui.composables.search.SearchTextFieldBackground
import com.example.lastfmuselessapp.ui.composables.search.SearchTextFieldToggleableClearTextAndPhotoActionIcon

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DiscoverScreen(
    searchText: String,
    focused: Boolean,
    availableSearchCategories: List<SearchCategoryModel>,
    selectedSearchCategory: SearchCategoryModel.SearchCategory,
    searchableItemsList: List<Searchable>,
    onSearchTextChanged: (String) -> Unit,
    onSearchTextCleared: () -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onSearchClicked: () -> Unit,
    onSelectedSearchCategoryChanged: (SearchCategoryModel.SearchCategory) -> Unit
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
                SearchTextFieldBackButton(focused)
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
}
