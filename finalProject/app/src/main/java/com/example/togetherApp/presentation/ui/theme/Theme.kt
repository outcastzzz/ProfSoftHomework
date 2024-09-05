package com.example.togetherApp.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

@Composable
internal fun TogetherAppTheme(
    content: @Composable () -> Unit
) {

    val systemIsDark = isSystemInDarkTheme()
    val isDarkSide = remember { mutableStateOf(systemIsDark) }

    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkSide,
        LocalTogetherColor provides palette,
        LocalTogetherType provides typography,
        content = {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.White),
            ) {
                content.invoke()
            }
        }
    )
}