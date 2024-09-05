package com.example.togetherApp.presentation.feature.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.togetherApp.domain.useCase.GetCommunityNotesUseCase
import com.example.togetherApp.domain.useCase.GetListOfCoursesUseCase
import com.example.togetherApp.domain.useCase.GetUserNotesUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.main.model.MainAction
import com.example.togetherApp.presentation.feature.main.model.MainEvent
import com.example.togetherApp.presentation.feature.main.model.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getListOfCoursesUseCase: GetListOfCoursesUseCase,
    private val getUserNotesUseCase: GetUserNotesUseCase,
    private val getCommunityNotesUseCase: GetCommunityNotesUseCase,
    imageLoader: ImageLoader,
) : BaseViewModel<MainViewState, MainAction, MainEvent>(initialState = MainViewState()) {

    init {
        viewState = viewState.copy(imageLoader = imageLoader)
        loadData()
    }

    override fun obtainEvent(viewEvent: MainEvent) {
        when (viewEvent) {
            is MainEvent.ClickCourse -> TODO()
            is MainEvent.ClickNote -> TODO()
            MainEvent.OpenAllCoursesClicked -> viewAction = MainAction.OpenAllCourses
            MainEvent.OpenCommunityNotesClicked -> viewAction = MainAction.OpenCommunityNotes
            MainEvent.OpenUserNotesClicked -> viewAction = MainAction.OpenUserNotes
            is MainEvent.SearchInputChanged -> viewState =
                viewState.copy(inputValue = viewEvent.newValue)
        }
    }

    private fun loadData() {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getListOfCoursesUseCase().collect { listOfCourses ->
                    viewState = viewState.copy(listOfCourses = listOfCourses)
                }
                getCommunityNotesUseCase().collect { listOfNotes ->
                    viewState = if (listOfNotes.isEmpty()) {
                        viewState.copy(isLoading = false)
                    } else {
                        viewState.copy(communityLastNote = listOfNotes.last())
                    }
                }
                getUserNotesUseCase().collect { listOfNotes ->
                    viewState = if (listOfNotes.isEmpty()) {
                        viewState.copy(isLoading = false)
                    } else {
                        viewState.copy(userLastNote = listOfNotes.last())
                    }
                }
            }
                .onSuccess {
                    viewState = viewState.copy(isLoading = false, isError = false)
                }
                .onFailure {
                    Log.d("errorTag", "ERROR: ${it.message}")
                    viewState = viewState.copy(isLoading = false, isError = true)
                    viewAction = MainAction.OpenAuth
                }
        }
    }
}