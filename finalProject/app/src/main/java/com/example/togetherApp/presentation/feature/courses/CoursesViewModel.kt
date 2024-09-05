package com.example.togetherApp.presentation.feature.courses

import androidx.lifecycle.viewModelScope
import com.example.togetherApp.domain.useCase.GetListOfCoursesUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.courses.model.CoursesAction
import com.example.togetherApp.presentation.feature.courses.model.CoursesEvent
import com.example.togetherApp.presentation.feature.courses.model.CoursesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val getListOfCoursesUseCase: GetListOfCoursesUseCase,
): BaseViewModel<CoursesViewState, CoursesAction, CoursesEvent>(initialState = CoursesViewState()) {

    init {
        loadData()
    }

    override fun obtainEvent(viewEvent: CoursesEvent) {
        when(viewEvent) {
            CoursesEvent.ClickBack -> viewAction = CoursesAction.ClickBack
            is CoursesEvent.SearchInputChanged -> {
                viewState = viewState.copy(searchInput = viewEvent.newValue)
                filterList(viewEvent.newValue)
            }
        }
    }

    private fun loadData() {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getListOfCoursesUseCase().collect { courses ->
                    viewState = viewState.copy(courses = courses)
                }
            }
                .onSuccess {
                    viewState = viewState.copy(isLoading = false, isError = false)
                }
                .onFailure {
                    viewState = viewState.copy(isLoading = false, isError = false)
                }
        }
    }

    private fun filterList(input: String) {
        val filteredList = viewState.courses.filter { course ->
            if(input.isEmpty()) {
                return
            } else {
                course.title.contains(input, ignoreCase = true)
            }
        }
        viewState = viewState.copy(courses = filteredList)
    }

}