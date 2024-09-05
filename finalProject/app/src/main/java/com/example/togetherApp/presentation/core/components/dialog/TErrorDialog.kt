package com.example.togetherApp.presentation.core.components.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.compose123.R
import com.example.togetherApp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun TErrorDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(247.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = TogetherTheme.colors.tintColor
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_error),
                    contentDescription = "error image",
                    tint = Color.White
                )
                Spacer(modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.something_was_going_wrong),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.try_again),
                    modifier = modifier
                        .clickable {

                        },
                    fontSize = 12.sp,
                    color = TogetherTheme.colors.primaryBackground
                )
            }
        }
    }

}

@Composable
@Preview
fun ErrorDialog_Preview() {
    TogetherAppTheme {
        TErrorDialog {}
    }
}