package com.example.togetherApp.presentation.feature.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose123.R
import com.example.togetherApp.presentation.core.components.topBar.TTopBar
import com.example.togetherApp.presentation.feature.main.model.MainEvent
import com.example.togetherApp.presentation.feature.main.model.MainViewState
import com.example.togetherApp.presentation.ui.common.AllPanel
import com.example.togetherApp.presentation.ui.common.CommunityNote
import com.example.togetherApp.presentation.ui.common.CourseItem
import com.example.togetherApp.presentation.ui.common.ErrorScreen
import com.example.togetherApp.presentation.ui.common.Loading
import com.example.togetherApp.presentation.ui.common.UserNote
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    modifier: Modifier = Modifier,
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = TogetherTheme.colors.primaryBackground,
        topBar = {
            TTopBar(
                title = stringResource(R.string.main),
                searchInput = viewState.inputValue,
                canGoBack = false,
                canSearch = true,
                onValueChange = {
                    eventHandler(MainEvent.SearchInputChanged(it))
                }
            )
        },
    ) { paddingValues ->

        when {
            viewState.isLoading -> Loading()
            viewState.isError -> ErrorScreen { }
            else -> {

                val listOfCourses = viewState.listOfCourses ?: emptyList()
                val scrollState = rememberScrollState()

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(TogetherTheme.colors.secondaryBackground)
                        .scrollable(scrollState, Orientation.Vertical),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = modifier.height(20.dp))
                    AllPanel(text = stringResource(R.string.your_courses)) {
                        eventHandler(MainEvent.OpenAllCoursesClicked)
                    }
                    Spacer(modifier = modifier.height(12.dp))

                    val pagerState = rememberPagerState { listOfCourses.size }

                    if (listOfCourses.isEmpty()) {
                        Box(
                            modifier = modifier
                                .height(160.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.you_havent_added_any_courses_yet),
                                style = TogetherTheme.type.titleSmall
                                    .copy(color = Color(0xFF646464))
                            )
                        }

                    } else {
                        HorizontalPager(
                            state = pagerState,
                            modifier = modifier
                                .fillMaxWidth()
                                .height(160.dp),
                        ) { page ->
                            CourseItem(course = listOfCourses[page])
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier
                                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                        ) {
                            repeat(listOfCourses.size) { index ->
                                val color =
                                    if (pagerState.currentPage == index) Color.Black else Color.Gray
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(2.dp)
                                        .background(color)
                                        .padding(horizontal = 2.dp),
                                )
                            }
                        }
                    }
                    Spacer(modifier = modifier.height(24.dp))
                    AllPanel(text = stringResource(R.string.your_notes)) {
                        eventHandler(MainEvent.OpenUserNotesClicked)
                    }
                    Spacer(modifier = modifier.height(12.dp))
                    if (viewState.userLastNote != null) {
                        UserNote()
                    } else {
                        Box(
                            modifier = modifier
                                .height(112.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.you_havent_added_any_notes_yet),
                                style = TogetherTheme.type.titleSmall
                                    .copy(color = Color(0xFF646464))
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(20.dp))
                    AllPanel(text = stringResource(R.string.community_notes)) {
                        eventHandler(MainEvent.OpenCommunityNotesClicked)
                    }
                    Spacer(modifier = modifier.height(12.dp))
                    if (viewState.communityLastNote != null) {
                        CommunityNote(
                            note = viewState.communityLastNote,
                            imageLoader = viewState.imageLoader!!
                        )
                    } else {
                        Box(
                            modifier = modifier
                                .height(112.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "А где все заметки?",
                                style = TogetherTheme.type.titleSmall
                                    .copy(color = Color(0xFF646464))
                            )
                        }
                    }
                }
            }
        }
    }
}






