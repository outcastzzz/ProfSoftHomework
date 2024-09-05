package com.example.togetherApp.presentation.feature.details

import android.util.Log
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.togetherApp.domain.useCase.GetCourseByIdUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.details.model.DetailsAction
import com.example.togetherApp.presentation.feature.details.model.DetailsEvent
import com.example.togetherApp.presentation.feature.details.model.DetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    imageLoader: ImageLoader,
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
): BaseViewModel<DetailsViewState, DetailsAction, DetailsEvent>(initialState = DetailsViewState()) {

    init {
        viewState = viewState.copy(imageLoader = imageLoader)
    }

    override fun obtainEvent(viewEvent: DetailsEvent) {
        when(viewEvent) {
            is DetailsEvent.ChangeIsFavourite -> TODO()
            is DetailsEvent.LoadData -> loadData(viewEvent.courseId)
            DetailsEvent.ClickBack -> viewAction = DetailsAction.ClickBack
        }
    }

    private fun loadData(courseId: String) {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getCourseByIdUseCase(courseId).collect { course ->
                    viewState = viewState.copy(course = course)
                }
            }
                .onSuccess {
                    viewState = viewState.copy(isLoading = false, isError = false)
                }
                .onFailure {
                    Log.d("errorTag", "ERROR: ${it.message}")
                    viewState = viewState.copy(isLoading = false, isError = true)
                }
        }
    }

}