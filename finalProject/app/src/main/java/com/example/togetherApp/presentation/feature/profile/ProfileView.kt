package com.example.togetherApp.presentation.feature.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose123.R
import com.example.togetherApp.presentation.core.components.switch.TSwitch
import com.example.togetherApp.presentation.feature.profile.model.ProfileEvent
import com.example.togetherApp.presentation.feature.profile.model.ProfileViewState
import com.example.togetherApp.presentation.ui.common.AllPanel
import com.example.togetherApp.presentation.ui.common.CourseItem
import com.example.togetherApp.presentation.ui.common.ErrorScreen
import com.example.togetherApp.presentation.ui.common.Loading
import com.example.togetherApp.presentation.ui.common.UserNote
import com.example.togetherApp.presentation.ui.extensions.countRole
import com.example.togetherApp.presentation.ui.extensions.formatPhoneNumber
import com.example.togetherApp.presentation.ui.extensions.toNormalDate
import com.example.togetherApp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    viewState: ProfileViewState,
    eventHandler: (ProfileEvent) -> Unit
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = TogetherTheme.colors.primaryBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.profile),
                        style = TogetherTheme.type.headlineMedium
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.ic_all_users),
                        contentDescription = "show all users",
                        modifier = modifier
                            .padding(end = 16.dp),
                        tint = Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TogetherTheme.colors.primaryBackground),
            )
        },
        floatingActionButton = {
            TextButton(
                onClick = {
                    eventHandler.invoke(ProfileEvent.LogoutClicked)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TogetherTheme.colors.primaryBackground
                ),
                contentPadding = PaddingValues(
                    vertical = 10.dp
                ),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.logout),
                    style = TogetherTheme.type.titleSmall
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
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
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val listOfCourses = viewState.profile?.courses ?: emptyList()
                        val pagerState = rememberPagerState { listOfCourses.size }
                        val lastNote = if (viewState.profile?.notes?.isNotEmpty() == true) {
                            viewState.profile.notes.last()
                        } else {
                            null
                        }

                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(TogetherTheme.colors.dividerColor)
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                            ) {
                                AsyncImage(
                                    model = viewState.profile?.avatar ?: "",
                                    contentDescription = "user profile image",
                                    modifier = modifier
                                        .size(100.dp)
                                        .padding(top = 20.dp)
                                        .clip(RoundedCornerShape(50)),
                                    imageLoader = viewState.imageLoader!!
                                )
                                Spacer(modifier = modifier.width(20.dp))
                                Column(
                                    modifier = modifier
                                        .padding(top = 20.dp),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = viewState.profile?.name ?: "Unknown",
                                        style = TogetherTheme.type.headlineMedium,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = viewState.profile?.surname ?: "Unknown",
                                        style = TogetherTheme.type.headlineMedium,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = stringResource(
                                            R.string.register_date,
                                            viewState.profile?.registerDate?.toNormalDate() ?: "???"
                                        ),
                                        style = TogetherTheme.type.titleSmall.copy(
                                            fontWeight = FontWeight.Normal,
                                            color = Color(0xFF646464)
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = stringResource(
                                            R.string.user_role,
                                            viewState.profile?.role?.countRole() ?: ""
                                        ),
                                        style = TogetherTheme.type.titleSmall.copy(
                                            fontWeight = FontWeight.Normal,
                                            color = Color(0xFF646464)
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            Text(
                                text = stringResource(
                                    R.string.user_phone,
                                    viewState.profile?.phone?.formatPhoneNumber() ?: "???"
                                ),
                                style = TogetherTheme.type.titleSmall
                                    .copy(fontWeight = FontWeight.Normal)
                            )
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.show_my_number),
                                    style = TogetherTheme.type.titleSmall
                                        .copy(fontWeight = FontWeight.Normal)
                                )
                                Spacer(modifier = modifier.weight(1f))
                                TSwitch(
                                    isChecked = viewState.isPhoneVisible,
                                    onCheckedChange = { visibility ->
                                        eventHandler.invoke(
                                            ProfileEvent.ChangePhoneVisibility(
                                                visibility
                                            )
                                        )
                                    }
                                )
                            }
                        }
                        Spacer(modifier = modifier.height(20.dp))
                        AllPanel(text = stringResource(R.string.your_courses)) {
                            eventHandler(ProfileEvent.UserCoursesClicked)
                        }
                        Spacer(modifier = modifier.height(12.dp))
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
                        Spacer(modifier = modifier.height(20.dp))
                        AllPanel(text = stringResource(R.string.your_notes)) {
                            eventHandler(ProfileEvent.UserNotesClicked)
                        }
                        Spacer(modifier = modifier.height(12.dp))
                        if (lastNote != null) {
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
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ProfileView_Preview() {
    TogetherAppTheme {
        ProfileView(viewState = ProfileViewState()) { }
    }
}