package com.example.togetherApp.presentation.feature.communityNotes

import android.util.Log
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.togetherApp.domain.useCase.GetCommunityNotesUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.communityNotes.model.CNotesAction
import com.example.togetherApp.presentation.feature.communityNotes.model.CNotesEvent
import com.example.togetherApp.presentation.feature.communityNotes.model.CNotesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CNotesViewModel @Inject constructor(
    private val getCommunityNotesUseCase: GetCommunityNotesUseCase,
    imageLoader: ImageLoader,
) : BaseViewModel<CNotesViewState, CNotesAction, CNotesEvent>(initialState = CNotesViewState()) {

    init {
        viewState = viewState.copy(imageLoader = imageLoader)
        loadData()
    }

    override fun obtainEvent(viewEvent: CNotesEvent) {
        when (viewEvent) {
            CNotesEvent.ClickBack -> viewAction = CNotesAction.ClickBack
            is CNotesEvent.SearchInputChanged -> {
                viewState = viewState.copy(searchInput = viewEvent.newValue)
                filterList(viewEvent.newValue)
            }
        }
    }

    private fun loadData() {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getCommunityNotesUseCase().collect { notes ->
                    viewState = viewState.copy(communityNotes = notes)
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

    private fun filterList(input: String) {
        val filteredList = viewState.communityNotes.filter { note ->
            if(input.isEmpty()) {
                return
            } else {
                note.title.contains(input, ignoreCase = true)
            }
        }
        viewState = viewState.copy(communityNotes = filteredList)
    }

}