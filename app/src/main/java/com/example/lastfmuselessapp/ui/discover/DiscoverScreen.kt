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
import androidx.compose.ui.Alignment
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

    val (focused, onFocusChanged) = remember { mutableStateOf(false) }

    SearchTextFieldBackground(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
    ) {
        SearchTextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            text = text,
            focused = focused,
            onTextChanged = onTextChange,
            onFocusChanged = onFocusChanged
        )
    }
}

@Composable
fun SearchTextFieldBackground(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
        shape = RectangleShape
    ) {
        Row(
            modifier = modifier.animateContentSize(animationSpec = TweenSpec(300)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
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
    focused: Boolean = false,
    onTextChanged: (String) -> Unit, // TODO u VM napravi replace "\n" u "" i "\r\n" u ""
    onFocusChanged: (Boolean) -> Unit
) {
    val backArrowAlpha by animateFloatAsState(if (focused) 1f else 0f)

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    val padding = animateDpAsState(targetValue = if (focused) 0.dp else 12.dp)
    val trailingIconAspectRatio = animateFloatAsState(targetValue = if (focused) 0.7f else 1f)

    TextField(
        value = if(focused) text else "",
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
                        .aspectRatio(trailingIconAspectRatio.value)
                        .fillMaxHeight()
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
                    .aspectRatio(trailingIconAspectRatio.value)
                    .fillMaxHeight()
            )
        },
        modifier = modifier
            .padding(padding.value)
            .onFocusEvent { onFocusChanged(it.isFocused) }
            .focusRequester(focusRequester)
    )
}
