package com.example.lastfmuselessapp.ui.composables.search

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
    hintText: String,
    focused: Boolean = false,
    onTextChanged: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onSearchClicked: () -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit
) {
    val padding = animateDpAsState(targetValue = if (focused) 0.dp else 12.dp)

    Box(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = text,
            onValueChange = onTextChanged,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClicked()
            }),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            modifier = modifier
                .padding(padding.value)
                .onFocusEvent {
                    onFocusChanged(it.isFocused)
                }
        )

        SearchTextFieldHint(visible = !focused, hintText = hintText)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchTextFieldBackButton(textFieldFocused: Boolean) {

    val focusManager = LocalFocusManager.current

    val iconAspectRation = animateFloatAsState(targetValue = if (textFieldFocused) 0.7f else 1f)

    AnimatedVisibility(
        visible = textFieldFocused,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Icon(
            Icons.Default.ArrowBack,
            stringResource(id = R.string.back_arrow),
            modifier = Modifier
                .aspectRatio(iconAspectRation.value)
                .fillMaxHeight()
                .clickable { focusManager.clearFocus() }
                .padding(8.dp)
        )
    }
}

@Composable
fun SearchTextFieldToggleableClearTextAndPhotoActionIcon(
    textFieldFocused: Boolean,
    textEmpty: Boolean,
    onTextCleared: () -> Unit,
    onPhotoActionClicked: () -> Unit
) {

    val trailingIconAspectRatio = animateFloatAsState(targetValue = if (textFieldFocused) 0.7f else 1f)

    Crossfade(targetState = textEmpty) { isEmpty ->
        when (isEmpty) {
            true -> {
                Icon(
                    Icons.Default.PhotoCamera,
                    stringResource(id = R.string.camera_icon),
                    modifier = Modifier
                        .clickable { onPhotoActionClicked() }
                        .padding(8.dp)
                        .aspectRatio(trailingIconAspectRatio.value)
                        .fillMaxHeight()
                )
            }
            false -> {
                Icon(
                    Icons.Default.Clear,
                    stringResource(id = R.string.clear_text_icon),
                    modifier = Modifier
                        .clickable { onTextCleared() }
                        .padding(8.dp)
                        .aspectRatio(trailingIconAspectRatio.value)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchTextFieldHint(visible: Boolean, hintText: String) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = hintText)
        }
    }
}
