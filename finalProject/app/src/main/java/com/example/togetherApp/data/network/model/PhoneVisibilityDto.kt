package com.example.togetherApp.data.network.model

import com.google.gson.annotations.SerializedName

data class PhoneVisibilityDto(
    @SerializedName("isVisible")
    val isVisible: Boolean
)