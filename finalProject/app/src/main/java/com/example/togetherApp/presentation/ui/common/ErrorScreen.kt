package com.example.togetherApp.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose123.R
import com.example.togetherApp.presentation.core.components.button.TButton
import com.example.togetherApp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherApp.presentation.ui.theme.TogetherTheme


@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TogetherTheme.colors.secondaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            painter = painterResource(R.drawable.ic_error_logo),
            contentDescription = "logo",
            modifier = modifier
                .size(69.77.dp, 77.67.dp)
        )
        Spacer(modifier = modifier.height(9.33.dp))
        Text(
            text = stringResource(R.string.oops),
            style = TogetherTheme.type.headlineLarge
        )
        Spacer(modifier = modifier.height(5.dp))
        Text(
            text = stringResource(R.string.something_goes_wrong),
            style = TogetherTheme.type.titleMedium.copy(color = Color(0xFF646464))
        )
        Spacer(modifier = modifier.height(12.dp))
        TButton(
            modifier = modifier
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.retry),
        ) {
            onClickRetry()
        }
    }

}

@Composable
@Preview
fun ErrorScreen_Prev() {
    TogetherAppTheme {
        ErrorScreen {  }
    }
}