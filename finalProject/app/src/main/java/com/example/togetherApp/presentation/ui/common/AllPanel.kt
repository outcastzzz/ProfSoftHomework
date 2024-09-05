package com.example.togetherApp.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose123.R
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
internal fun AllPanel(
    modifier: Modifier = Modifier,
    text: String,
    onAllClicked: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(TogetherTheme.colors.dividerColor)
    ) {
        Text(
            text = text,
            modifier = modifier
                .padding(
                    start = 11.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            style = TogetherTheme.type.titleSmall
        )
        Spacer(modifier.weight(1f))
        VerticalDivider(
            modifier = modifier.height(32.dp),
            thickness = 2.dp,
            color = Color.White,
        )
        Text(
            text = stringResource(R.string.all),
            modifier = modifier
                .padding(
                    start = 12.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 12.dp
                )
                .clickable {
                    onAllClicked()
                },
            style = TogetherTheme.type.titleSmall.copy(
                color = Color(0xFF646464)
            )
        )
    }
}