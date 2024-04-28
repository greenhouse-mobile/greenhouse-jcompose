package com.integradis.greenhouse.shared.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun MultiStyleText(
    firstTextPart: String,
    firstColor: Color,
    secondTextPart: String,
    secondColor: Color,
) {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = firstColor)){
            append(firstTextPart)
        }
        withStyle(style = SpanStyle(color = secondColor)){
            append(secondTextPart)
        }
    })
}
