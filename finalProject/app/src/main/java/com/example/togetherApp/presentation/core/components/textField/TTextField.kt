package com.example.togetherApp.presentation.core.components.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togetherApp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun TTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit,
) {

    BaseTextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(TogetherTheme.colors.onPrimaryBackground),
        enabled = enabled,
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                modifier = Modifier
                    .padding(start = 0.dp),
                style = TogetherTheme.type.bodyMedium.copy(
                    color = TogetherTheme.colors.primaryText,
                    fontWeight = FontWeight.Medium
                )
            )
        },
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = TogetherTheme.colors.onPrimaryBackground,
            unfocusedContainerColor = TogetherTheme.colors.onPrimaryBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        textStyle = TogetherTheme.type.bodyMedium.copy(
            color = TogetherTheme.colors.primaryText,
            fontWeight = FontWeight.Medium
        ),
    )

}

@Composable
@Preview(showBackground = false)
fun FTextField_Preview() {
    TogetherAppTheme {
        TTextField(
            text = "",
            hint = "Email",
        ) {

        }
    }
}