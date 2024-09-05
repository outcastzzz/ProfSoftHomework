package com.example.togetherApp.presentation.feature.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.togetherApp.data.network.TokenManager
import com.example.togetherApp.domain.entity.send.PhoneVisibility
import com.example.togetherApp.domain.useCase.ChangePhoneVisibilityUseCase
import com.example.togetherApp.domain.useCase.GetProfileUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.profile.model.ProfileAction
import com.example.togetherApp.presentation.feature.profile.model.ProfileEvent
import com.example.togetherApp.presentation.feature.profile.model.ProfileViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val changePhoneVisibilityUseCase: ChangePhoneVisibilityUseCase,
    imageLoader: ImageLoader,
    private val getProfileUseCase: GetProfileUseCase,
    private val tokenManager: TokenManager
): BaseViewModel<ProfileViewState, ProfileAction, ProfileEvent>(initialState = ProfileViewState()) {

    init {
        viewState = viewState.copy(imageLoader = imageLoader)
        loadData()
    }

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when(viewEvent) {
            ProfileEvent.AllUsersClicked -> viewAction = ProfileAction.OpenAllUsers
            is ProfileEvent.ChangePhoneVisibility -> {
                changePhoneVisibility(viewState.isPhoneVisible)
            }
            ProfileEvent.LogoutClicked -> logout()
            ProfileEvent.UserCoursesClicked -> viewAction = ProfileAction.OpenUserCourses
            ProfileEvent.UserNotesClicked -> viewAction = ProfileAction.OpenUserNotes
        }
    }

    private fun loadData() {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getProfileUseCase().collect { profile ->
                    viewState = viewState.copy(profile = profile)
                }
            }
                .onSuccess {
                    withContext(Dispatchers.Main) {
                        viewState = viewState.copy(isLoading = false, isError = false)
                    }
                }
                .onFailure {
                    withContext(Dispatchers.Main) {
                        viewState = viewState.copy(isLoading = false, isError = true)
                    }
                }
        }
    }

    private fun changePhoneVisibility(isVisible: Boolean) {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                changePhoneVisibilityUseCase(PhoneVisibility(isVisible))
            }
                .onSuccess { profile ->
                    viewState = viewState.copy(
                        profile = profile,
                        isPhoneVisible = !viewState.isPhoneVisible,
                        isLoading = false
                    )
                }
                .onFailure {
                    Log.d("errorTag", "ERROR: ${it.message}")
                    viewState = viewState.copy(isLoading = false, isError = true)
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            tokenManager.deleteToken()
        }
        viewAction = ProfileAction.Logout
    }

}