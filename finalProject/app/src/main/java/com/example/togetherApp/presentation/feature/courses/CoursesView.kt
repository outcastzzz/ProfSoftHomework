package com.example.togetherApp.presentation.feature.courses

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
import com.example.togetherApp.presentation.feature.courses.model.CoursesEvent
import com.example.togetherApp.presentation.feature.courses.model.CoursesViewState
import com.example.togetherApp.presentation.ui.common.CourseItem
import com.example.togetherApp.presentation.ui.common.ErrorScreen
import com.example.togetherApp.presentation.ui.common.Loading
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun CoursesView(
    modifier: Modifier = Modifier,
    viewState: CoursesViewState,
    eventHandler: (CoursesEvent) -> Unit,
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = TogetherTheme.colors.primaryBackground,
        topBar = {
            TTopBar(
                title = stringResource(R.string.all_courses),
                searchInput = viewState.searchInput,
                canSearch = true,
                canGoBack = true,
                onValueChange = {
                    eventHandler(CoursesEvent.SearchInputChanged(it))
                }
            ) { eventHandler(CoursesEvent.ClickBack) }
        },
    ) { paddingValues ->
        when {
            viewState.isLoading -> Loading()
            viewState.isError -> ErrorScreen {  }
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
                        itemsIndexed(viewState.courses.reversed()) { index, course ->
                            val paddingModifier = when(index) {
                                0 -> Modifier.padding(top = 20.dp)
                                viewState.courses.size - 1 -> Modifier.padding(bottom = 20.dp)
                                else -> Modifier
                            }
                            CourseItem(
                                modifier = paddingModifier,
                                course = course
                            )
                        }
                    }
                }
            }
        }
    }
}