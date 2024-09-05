package com.example.togetherApp.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.compose123.R
import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.entity.get.Note
import com.example.togetherApp.presentation.ui.extensions.toSmallDate
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CourseItem(modifier: Modifier = Modifier, course: Course) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = TogetherTheme.colors.primaryBackground
        )
    ) {
        Text(
            text = course.title,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 87.dp,
                ),
            style = TogetherTheme.type.titleLarge,
        )
        Spacer(modifier = Modifier.height(18.dp))
        FlowRow(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 12.dp
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in course.tags) {
                Tag(text = i)
            }
        }
    }
}

@Composable
fun UserNote(
    modifier: Modifier = Modifier,
    note: Note? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(112.dp)
            .padding(horizontal = 16.dp)
            .background(Color.Transparent),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(TogetherTheme.colors.primaryBackground)
        ) {

            Text(
                text = "Для создания новой Activity",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        start = 12.dp,
                        end = 83.dp
                    ),
                maxLines = 2,
                style = TogetherTheme.type.bodyLarge,
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = "Нужно лишь применить старый дедовский визард",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                style = TogetherTheme.type.titleSmall.copy(
                    color = Color(0xFF806B00),
                    fontWeight = FontWeight.Normal
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.height(12.dp))
        }
        Box(
            modifier = modifier
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(12.dp))
                .background(TogetherTheme.colors.tintColor)
        ) {
            Text(
                text = "12 июня",
                modifier = modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                style = TogetherTheme.type.bodyMedium.copy(color = Color.White)
            )
        }
    }
}

@Composable
fun CommunityNote(
    modifier: Modifier = Modifier,
    note: Note? = null,
    imageLoader: ImageLoader
) {
    note?.let {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(124.dp)
                .background(Color.Transparent),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(TogetherTheme.colors.primaryBackground)
            ) {
                Spacer(modifier = Modifier.height(26.dp))

                val parts = note.title.split(" ", limit = 2)
                val firstWord = parts.getOrNull(0) ?: ""
                val remainingText = parts.getOrNull(1) ?: ""

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = TogetherTheme.type.bodyLarge.toSpanStyle()) {
                            append(firstWord)
                            append("\n")
                            append(remainingText)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp,
                            end = 83.dp
                        ),
                    maxLines = 2,
                    style = TogetherTheme.type.bodyLarge,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = note.content[0].text ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp,
                            end = 12.dp
                        ),
                    maxLines = 1,
                    style = TogetherTheme.type.titleSmall
                        .copy(
                            color = TogetherTheme.colors.onPrimaryText,
                            fontWeight = FontWeight.Normal
                        ),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(TogetherTheme.colors.tintColor)
                ) {
                    val avatar = note.author.avatar
                    AsyncImage(
                        model = avatar.ifEmpty { R.drawable.ic_default_user },
                        contentDescription = "note`s author`s avatar",
                        modifier = Modifier
                            .padding(
                                top = 8.dp,
                                bottom = 8.dp,
                                start = 12.dp
                            )
                            .size(14.dp)
                            .clip(RoundedCornerShape(50)),
                        imageLoader = imageLoader,
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(
                            R.string.full_name,
                            note.author.name,
                            note.author.surname
                        ),
                        modifier = Modifier
                            .padding(
                                end = 12.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            ),
                        style = TogetherTheme.type.bodyMedium.copy(color = Color.White)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(TogetherTheme.colors.tintColor)
                ) {
                    Text(
                        text = note.date.toSmallDate(),
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        style = TogetherTheme.type.bodyMedium.copy(color = Color.White)
                    )
                }
            }

        }
    }
}


@Composable
fun Tag(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .border(1.dp, Color.Black, shape = ShapeDefaults.ExtraSmall),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp),
            style = TogetherTheme.type.bodyMedium
        )
    }
}