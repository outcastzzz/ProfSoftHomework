package com.example.togetherApp.presentation.feature.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose123.R
import com.example.togetherApp.presentation.core.components.button.TButton
import com.example.togetherApp.presentation.core.components.dialog.TErrorDialog
import com.example.togetherApp.presentation.core.components.textField.TTextField
import com.example.togetherApp.presentation.feature.auth.model.AuthEvent
import com.example.togetherApp.presentation.feature.auth.model.AuthViewState
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun AuthView(
    modifier: Modifier = Modifier,
    viewState: AuthViewState,
    eventHandler: (AuthEvent) -> Unit,
) {
    when {
        viewState.isError -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                TErrorDialog(
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    eventHandler.invoke(AuthEvent.ClearInputs)
                }
            }
        }
        else -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(TogetherTheme.colors.primaryBackground)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(TogetherTheme.colors.primaryBackground),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "main logo",
                        modifier = modifier
                            .padding(top = 70.dp, bottom = 69.dp)
                    )
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.sign_in),
                            style = TogetherTheme.type.titleLarge,
                        )
                        Spacer(modifier.height(6.dp))
                        Text(
                            text = stringResource(R.string.sign_in_to_continue),
                            style = TogetherTheme.type.titleSmall.copy(fontWeight = FontWeight.Normal),
                        )
                        Spacer(modifier.height(12.dp))
                        TTextField(
                            text = viewState.phoneValue,
                            hint = stringResource(R.string.phone_number),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        ) { eventHandler.invoke(AuthEvent.PhoneChanged(it)) }
                        Spacer(modifier.height(12.dp))
                        TTextField(
                            text = viewState.passwordValue,
                            hint = stringResource(R.string.password)
                        ) { eventHandler.invoke(AuthEvent.PasswordChanged(it)) }
                    }
                    Spacer(modifier.weight(1f))
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TButton(
                            text = stringResource(R.string.sign_in),
                            isLoading = viewState.isLoading
                        ) {
                            eventHandler.invoke(AuthEvent.LoginClicked)
                        }
                        Spacer(modifier.height(12.dp))
                        Button(
                            modifier = modifier
                                .fillMaxWidth(),
                            onClick = { eventHandler.invoke(AuthEvent.SignUpClicked) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.sign_up),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
