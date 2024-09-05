package com.example.togetherApp.presentation.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.compose123.R
import com.example.togetherApp.presentation.feature.splash.model.SplashEvent
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun SplashView(
    modifier: Modifier = Modifier,
    eventHandler: (SplashEvent) -> Unit,
) {

    LaunchedEffect(Unit) {
       eventHandler(SplashEvent.CheckToken)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TogetherTheme.colors.primaryBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "just splash screen"
        )
    }

}