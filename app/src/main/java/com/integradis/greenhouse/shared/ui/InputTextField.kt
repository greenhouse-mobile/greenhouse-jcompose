package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.integradis.greenhouse.ui.theme.GrayTextField40
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun InputTextField(
    input: MutableState<String>,
    placeholder: String,
    ) {
    TextField(
        placeholder = {
            Text(text = placeholder, style = Typography.labelMedium)
        },
        value = input.value,
        onValueChange = {
            input.value = it
        },
        trailingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = "Buscar")
        },
        maxLines = 1,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GrayTextField40,
            unfocusedContainerColor = GrayTextField40,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = PrimaryGreen40,
            selectionColors = TextSelectionColors(
                handleColor = PrimaryGreen40,
                backgroundColor = PrimaryGreen40
            )
        ),
        textStyle = Typography.labelMedium,
    )
}