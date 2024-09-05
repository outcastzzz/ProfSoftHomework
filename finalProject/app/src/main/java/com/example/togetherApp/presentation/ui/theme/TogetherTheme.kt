package com.example.togetherApp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class TogetherColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val onPrimaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val dividerColor: Color,
    val onPrimaryText: Color,
    val tertiaryText: Color,
)

data class TogetherType(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
)

object TogetherTheme {
    val colors: TogetherColors
        @Composable
        get() = LocalTogetherColor.current
    val type: TogetherType
        @Composable
        get() = LocalTogetherType.current
}

val LocalTogetherColor = staticCompositionLocalOf<TogetherColors> { error("No default implementation for colors") }
val LocalTogetherType = staticCompositionLocalOf<TogetherType> { error("No default implementation for typography") }