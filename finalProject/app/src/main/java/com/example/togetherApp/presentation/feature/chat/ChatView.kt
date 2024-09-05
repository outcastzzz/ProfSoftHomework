package com.example.togetherApp.presentation.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.compose123.R
import com.example.togetherApp.domain.entity.get.ChatItem
import com.example.togetherApp.domain.entity.send.Message
import com.example.togetherApp.presentation.core.components.topBar.TTopBar
import com.example.togetherApp.presentation.feature.chat.model.ChatEvent
import com.example.togetherApp.presentation.feature.chat.model.ChatViewState
import com.example.togetherApp.presentation.ui.common.ErrorScreen
import com.example.togetherApp.presentation.ui.common.Loading
import com.example.togetherApp.presentation.ui.extensions.toChatDate
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatView(
    modifier: Modifier = Modifier,
    viewState: ChatViewState,
    eventHandler: (ChatEvent) -> Unit,
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = TogetherTheme.colors.primaryBackground,
        topBar = {
            TTopBar(
                title = stringResource(R.string.chat),
                searchInput = viewState.searchInput,
                canSearch = true,
                canGoBack = false,
                onValueChange = {
                    eventHandler(ChatEvent.SearchInputChanged(it))
                }
            )
        },
    ) { paddingValues ->

        when {
            viewState.isLoading -> Loading()
            viewState.isError -> ErrorScreen { }
            else -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(TogetherTheme.colors.secondaryBackground),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    val listState = rememberLazyListState()
                    LazyColumn(
                        modifier = modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        state = listState,
                        reverseLayout = true,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(viewState.messages.reversed()) { index, message ->
                            val paddingModifier = when (index) {
                                0 -> Modifier.padding(bottom = 20.dp)
                                viewState.messages.size - 1 -> Modifier.padding(top = 20.dp)
                                else -> Modifier
                            }
                            if (message.author.id == viewState.profileId) {
                                UserMessageItem(
                                    modifier = paddingModifier,
                                    message = message,
                                    imageLoader = viewState.imageLoader!!
                                )
                            } else {
                                OtherUserMessageItem(
                                    modifier = paddingModifier,
                                    message = message,
                                    imageLoader = viewState.imageLoader!!
                                )
                            }

                        }
                    }
                    HorizontalDivider(color = TogetherTheme.colors.dividerColor)
                    TextField(
                        value = viewState.messageInput,
                        onValueChange = {
                            eventHandler(ChatEvent.MessageInputChanged(it))
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.message),
                                style = TogetherTheme.type.titleMedium
                                    .copy(color = TogetherTheme.colors.tertiaryText)
                            )
                        },
                        trailingIcon = {
                            Row {
                                Icon(
                                    painter = painterResource(R.drawable.ic_refresh),
                                    contentDescription = "refresh screen",
                                    modifier = modifier
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(
                                                bounded = false,
                                                radius = 15.dp,
                                            )
                                        ) {
                                            eventHandler.invoke(ChatEvent.ClickRefresh)
                                        },
                                    tint = TogetherTheme.colors.tertiaryText
                                )
                                Spacer(modifier.width(12.dp))
                                Icon(
                                    painter = painterResource(R.drawable.ic_send),
                                    contentDescription = "send message",
                                    modifier = modifier
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(
                                                bounded = false,
                                                radius = 15.dp,
                                            )
                                        ) {
                                            if (viewState.messageInput != "") {
                                                eventHandler.invoke(
                                                    ChatEvent.SendMessage(Message(viewState.messageInput))
                                                )
                                            }
                                        },
                                    tint = TogetherTheme.colors.tertiaryText
                                )
                                Spacer(modifier.width(16.dp))
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    HorizontalDivider(color = TogetherTheme.colors.dividerColor)
                }
            }
        }
    }
}

@Composable
fun OtherUserMessageItem(
    modifier: Modifier = Modifier,
    message: ChatItem,
    imageLoader: ImageLoader
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(end = 80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (message.author.role == 2)
                        TogetherTheme.colors.primaryBackground else TogetherTheme.colors.dividerColor
                )
        ) {
            val avatar = message.author.avatar
            AsyncImage(
                model = avatar.ifEmpty { R.drawable.ic_default_user },
                imageLoader = imageLoader,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
                    .align(Alignment.Top),
                contentDescription = "avatar"
            )
            Column(
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 8.dp,
                        end = 12.dp
                    )
            ) {
                Text(
                    text = stringResource(
                        R.string.full_name,
                        message.author.name,
                        message.author.surname
                    ),
                    style = TogetherTheme.type.bodyMedium
                        .copy(fontWeight = FontWeight.Bold),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message.message,
                    style = TogetherTheme.type.bodyMedium
                        .copy(color = Color(0xFF646464)),
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.Start),
                    maxLines = Int.MAX_VALUE,
                    overflow = TextOverflow.Clip
                )
            }
        }
        Spacer(Modifier.width(4.dp))
        Text(
            text = message.date.toChatDate(),
            style = TogetherTheme.type.bodyMedium
                .copy(color = Color(0xFF646464)),
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun UserMessageItem(
    modifier: Modifier = Modifier,
    message: ChatItem,
    imageLoader: ImageLoader
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            text = message.date.toChatDate(),
            style = TogetherTheme.type.bodyMedium
                .copy(color = Color(0xFF646464)),
            modifier = Modifier
                .align(Alignment.BottomStart)
        )
        Spacer(Modifier.width(4.dp))
        Row(
            modifier = Modifier
                .padding(start = 80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (message.author.role == 2)
                        TogetherTheme.colors.primaryBackground else TogetherTheme.colors.dividerColor
                )
                .align(Alignment.TopEnd)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 12.dp,
                        end = 8.dp
                    )
            ) {
                Text(
                    text = stringResource(
                        R.string.full_name,
                        message.author.name,
                        message.author.surname
                    ),
                    style = TogetherTheme.type.bodyMedium
                        .copy(fontWeight = FontWeight.Bold),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message.message,
                    style = TogetherTheme.type.bodyMedium
                        .copy(color = Color(0xFF646464)),
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.Start),
                    maxLines = Int.MAX_VALUE,
                    overflow = TextOverflow.Clip
                )
            }
            val avatar = message.author.avatar
            AsyncImage(
                model = avatar.ifEmpty { R.drawable.ic_default_user },
                imageLoader = imageLoader,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
                    .align(Alignment.Top),
                contentDescription = "avatar"
            )
        }
    }
}

