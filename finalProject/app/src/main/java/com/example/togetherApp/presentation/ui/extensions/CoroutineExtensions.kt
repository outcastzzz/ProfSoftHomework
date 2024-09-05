package com.example.togetherApp.presentation.ui.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.safeLaunch(
    onError: (Throwable) -> Unit,
    block: suspend CoroutineScope.() -> Unit
) {
    this.launch {
        try {
            block()
        } catch (e: Throwable) {
            onError(e)
        }
    }
}