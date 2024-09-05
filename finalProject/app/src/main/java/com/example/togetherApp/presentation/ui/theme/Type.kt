package com.example.togetherApp.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = TogetherType(
    headlineLarge = TextStyle(
        fontSize = 32.sp,
        lineHeight = 37.5.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = TextStyle(
        fontSize = 20.sp,
        lineHeight = 23.44.sp,
        color = Color.Black,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontSize = 28.sp,
        lineHeight = 32.81.sp,
        color = Color.Black,
        fontWeight = FontWeight.Medium
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 18.75.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.41.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.13.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.06.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontSize = 10.sp,
        lineHeight = 11.72.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal
    )
)