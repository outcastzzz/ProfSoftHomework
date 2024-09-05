package com.example.togetherApp.presentation.feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.togetherApp.presentation.feature.details.model.DetailsEvent
import com.example.togetherApp.presentation.feature.details.model.DetailsViewState
import com.example.togetherApp.presentation.ui.common.ErrorScreen
import com.example.togetherApp.presentation.ui.common.Loading
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsView(
    modifier: Modifier = Modifier,
    viewState: DetailsViewState,
    eventHandler: (DetailsEvent) -> Unit
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = TogetherTheme.colors.primaryBackground,
        topBar = {
            val interactionSource = remember { MutableInteractionSource() }
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.all_courses),
                        style = TogetherTheme.type.headlineMedium
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "go back",
                        modifier = modifier
                            .padding(start = 16.dp, end = 12.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = rememberRipple(
                                    bounded = false,
                                    radius = 15.dp,
                                )
                            ) {
                                eventHandler(DetailsEvent.ClickBack)
                            }
                    )
                },
                actions = {
                    Box(
                        modifier = modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(TogetherTheme.colors.onPrimaryBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = if (viewState.isFavourite) {
                                painterResource(R.drawable.ic_saved)
                            } else {
                                painterResource(R.drawable.ic_non_saved)
                            },
                            contentDescription = "search button",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TogetherTheme.colors.primaryBackground),
                modifier = modifier
                    .padding(end = 16.dp),
            )
        }
    ) { paddingValues ->

        when {
            viewState.isLoading -> Loading()
            viewState.isError -> ErrorScreen {}
            else -> {
                
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
                
            }
        }

    }

}