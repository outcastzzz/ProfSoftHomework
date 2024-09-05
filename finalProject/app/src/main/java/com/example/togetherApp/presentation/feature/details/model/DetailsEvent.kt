package com.example.togetherApp.presentation.feature.details.model

sealed class DetailsEvent {
    class ChangeIsFavourite(val isFavourite: Boolean): DetailsEvent()
    class LoadData(val courseId: String): DetailsEvent()
    data object ClickBack: DetailsEvent()
}