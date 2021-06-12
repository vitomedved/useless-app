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
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.lastfmuselessapp.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DiscoverScreen() {

    val (text, onTextChange) = rememberSaveable { mutableStateOf("") }

    var elevateSearchBar by remember { mutableStateOf(false) }

    SearchTextFieldBackground(
        elevate = elevateSearchBar,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        SearchTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            onTextChanged = onTextChange
        )
    }
}

@Composable
fun SearchTextFieldBackground(
    modifier: Modifier = Modifier,
    elevate: Boolean,
    content: @Composable RowScope.() -> Unit
) {

    val animatedElevation by animateDpAsState(
        targetValue = if (elevate) 1.dp else 0.dp,
        animationSpec = TweenSpec(500)
    )

    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
        elevation = animatedElevation,
        shape = RectangleShape
    ) {
        Row(
            modifier = modifier.animateContentSize(animationSpec = TweenSpec(300)),
            content = content
        )
    }
}

@ExperimentalAnimationApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit // TODO u VM napravi replace "\n" u "" i "\r\n" u ""
) {
    val (focused, onFocused) = remember { mutableStateOf(false) }

    val backArrowAlpha by animateFloatAsState(if (focused) 1f else 0f)

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current


    TextField(
        value = text,
        onValueChange = onTextChanged,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
        leadingIcon = {
            AnimatedVisibility(
                visible = focused,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    stringResource(id = R.string.back_arrow),
                    modifier = Modifier
                        .clickable { focusManager.clearFocus() }
                        .padding(8.dp)
                        .alpha(backArrowAlpha)
                )
            }
        },
        trailingIcon = {
            Icon(
                Icons.Default.PhotoCamera,
                stringResource(id = R.string.camera_icon),
                modifier = Modifier
                    .clickable { /*TODO*/ }
                    .padding(8.dp)
            )
        },
        modifier = modifier
            .onFocusEvent { onFocused(it.isFocused) }
            .focusRequester(focusRequester)
    )
}
