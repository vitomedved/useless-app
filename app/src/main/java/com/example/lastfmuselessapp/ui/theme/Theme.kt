package com.example.lastfmuselessapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Red800,
    primaryVariant = Red700,
    secondary = Grey800,
    secondaryVariant = Grey800,
    background = Grey800,
    surface = Grey800,
    error = Red700,
    onPrimary = Grey800,
    onSecondary = Grey300,
    onBackground = Grey300,
    onSurface = Grey300,
    onError = Grey800
)

private val LightColorPalette = lightColors(
    primary = Red400,
    primaryVariant = Red700,
    secondary = Grey200,
    secondaryVariant = Grey200,
    background = Color.White,
    surface = Grey300,
    error = Red700,
    onPrimary = Grey100,
    onSecondary = Grey800,
    onBackground = Grey800,
    onSurface = Grey800,
    onError = Grey100
)

@Composable
fun LastfmUselessAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}