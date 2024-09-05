package com.example.togetherApp.presentation.feature.communityNotes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose123.R
import com.example.togetherApp.presentation.core.components.topBar.TTopBar
import com.example.togetherApp.presentation.feature.communityNotes.model.CNotesEvent
import com.example.togetherApp.presentation.feature.communityNotes.model.CNotesViewState
import com.example.togetherApp.presentation.ui.common.CommunityNote
import com.example.togetherApp.presentation.ui.common.ErrorScreen
import com.example.togetherApp.presentation.ui.common.Loading
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun CNotesView(
    modifier: Modifier = Modifier,
    viewState: CNotesViewState,
    eventHandler: (CNotesEvent) -> Unit,
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = TogetherTheme.colors.primaryBackground,
        topBar = {
            TTopBar(
                title = stringResource(R.string.community_notes),
                canGoBack = true,
                canSearch = true,
                searchInput = viewState.searchInput,
                onValueChange = {
                    eventHandler(CNotesEvent.SearchInputChanged(it))
                }
            ) {
                eventHandler(CNotesEvent.ClickBack)
            }
        },
    ) { paddingValues ->

        when {
            viewState.isLoading -> Loading()
            viewState.isError -> ErrorScreen { }
            else -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(TogetherTheme.colors.secondaryBackground),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(viewState.communityNotes.reversed()) { index, note ->
                            val paddingModifier = when (index) {
                                0 -> Modifier.padding(top = 20.dp)
                                viewState.communityNotes.size - 1 -> Modifier.padding(bottom = 20.dp)
                                else -> Modifier
                            }
                            CommunityNote(
                                modifier = paddingModifier,
                                note = note,
                                imageLoader = viewState.imageLoader!!
                            )
                        }
                    }
                }
            }
        }
    }
}