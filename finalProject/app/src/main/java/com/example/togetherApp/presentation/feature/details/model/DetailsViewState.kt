package com.example.togetherApp.presentation.feature.details.model

import coil.ImageLoader
import com.example.togetherApp.domain.entity.get.Course

data class DetailsViewState(
    val course: Course? = null,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val isFavourite: Boolean = false,
    val imageLoader: ImageLoader? = null,
)