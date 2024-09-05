package com.example.togetherApp.presentation.feature.chat

import android.util.Log
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.togetherApp.domain.entity.send.Message
import com.example.togetherApp.domain.useCase.GetAllMessagesUseCase
import com.example.togetherApp.domain.useCase.GetProfileUseCase
import com.example.togetherApp.domain.useCase.SendMessageUseCase
import com.example.togetherApp.presentation.core.BaseViewModel
import com.example.togetherApp.presentation.feature.chat.model.ChatAction
import com.example.togetherApp.presentation.feature.chat.model.ChatEvent
import com.example.togetherApp.presentation.feature.chat.model.ChatViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    imageLoader: ImageLoader
): BaseViewModel<ChatViewState, ChatAction, ChatEvent>(initialState = ChatViewState()) {

    init {
        viewState = viewState.copy(imageLoader = imageLoader)
        loadData()
    }

    override fun obtainEvent(viewEvent: ChatEvent) {
        when(viewEvent) {
            ChatEvent.ClickRefresh -> TODO()
            is ChatEvent.SearchInputChanged -> {
                viewState = viewState.copy(searchInput = viewEvent.newValue)
                filterList(viewEvent.newValue)
            }

            is ChatEvent.MessageInputChanged -> viewState = viewState
                .copy(messageInput = viewEvent.newValue)
            is ChatEvent.SendMessage -> sendMessage(viewEvent.message)
        }
    }

    private fun loadData() {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getAllMessagesUseCase().collect { chat ->
                    viewState = viewState.copy(messages = chat)
                }
                getProfileUseCase().collect { profile ->
                    viewState = viewState.copy(profileId = profile.id)
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

    private fun sendMessage(message: Message) {
        viewState = viewState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                sendMessageUseCase(message = message).collect { newMessage ->
                    val updatedMessages = viewState.messages.toMutableList().apply {
                        add(newMessage)
                    }
                    viewState = viewState.copy(messages = updatedMessages)
                }
            }
                .onSuccess {
                    viewState = viewState.copy(isLoading = false, isError = false)
                    viewState = viewState.copy(messageInput = "")
                }
                .onFailure {
                    Log.d("errorTag", "ERROR: ${it.message}")
                    viewState = viewState.copy(isLoading = false, isError = true)
                }
        }
    }

    private fun filterList(input: String) {
        val filteredList = viewState.messages.filter { message ->
            if(input.isEmpty()) {
                return
            } else {
                message.message.contains(input, ignoreCase = true)
            }
        }
        viewState = viewState.copy(messages = filteredList)
    }

}