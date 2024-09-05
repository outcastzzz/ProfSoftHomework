package com.example.togetherApp.presentation.core.components.topBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose123.R
import com.example.togetherApp.presentation.core.components.textField.TTextField
import com.example.togetherApp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TTopBar(
    modifier: Modifier = Modifier,
    title: String,
    searchInput: String = "",
    canSearch: Boolean = true,
    canGoBack: Boolean = false,
    onValueChange: (String) -> Unit,
    onClickBack: () -> Unit = {},
) {

    var isSearchActive by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if(canSearch) {
                AnimatedVisibility(visible = !isSearchActive) {
                    Text(
                        text = title,
                        style = TogetherTheme.type.headlineMedium
                    )
                }
            }

        },
        navigationIcon = {
            if(canGoBack) {
                AnimatedVisibility(visible = !isSearchActive) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "go back",
                        modifier = modifier
                            .padding(start = 16.dp, end = 12.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    bounded = false,
                                    radius = 15.dp,
                                )
                            ) { onClickBack() }
                    )
                }
            }
        },
        actions = {
            if(canSearch) {
                AnimatedVisibility(visible = !isSearchActive) {
                    Box(
                        modifier = modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(TogetherTheme.colors.onPrimaryBackground)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    bounded = false,
                                )
                            ) { isSearchActive = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "search button",
                        )
                    }
                }
                AnimatedVisibility(visible = isSearchActive) {
                    TTextField(
                        modifier = Modifier.padding(start = 16.dp),
                        text = searchInput,
                        hint = stringResource(R.string.search),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = "search icon",
                                modifier = modifier
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(
                                            bounded = false
                                        )
                                    ) { isSearchActive = false }
                            )
                        },
                    ) { onValueChange(it) }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = TogetherTheme.colors.primaryBackground),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp)
    )
}

@Composable
@Preview
fun TTopBar_Preview() {
    TogetherAppTheme {
        TTopBar(
            title = "Заметки сообщества",
            searchInput = "",
            canGoBack = true,
            canSearch = false,
            onClickBack = {},
            onValueChange = {}
        )
    }
}