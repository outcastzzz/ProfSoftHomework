package com.example.togetherApp.presentation.core.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherApp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun TButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier.height(40.dp).fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TogetherTheme.colors.tintColor,
            disabledContainerColor = TogetherTheme.colors.tintColor
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(28.dp),
                color = TogetherTheme.colors.primaryBackground
            )
        } else {
            Text(
                text = text,
                color = if (enabled) TogetherTheme.colors.secondaryText else TogetherTheme.colors.secondaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun TButton_Preview() {
    TogetherAppTheme {
        TButton(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            text = "Регистрация",
            isLoading = false
        ) { }
    }
}