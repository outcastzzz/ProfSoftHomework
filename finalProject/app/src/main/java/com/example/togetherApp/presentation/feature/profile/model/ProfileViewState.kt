package com.example.togetherApp.presentation.feature.profile.model

import coil.ImageLoader
import com.example.togetherApp.domain.entity.get.Profile

data class ProfileViewState(
    val profile: Profile? = null,
    val isPhoneVisible: Boolean = true,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val imageLoader: ImageLoader? = null
)