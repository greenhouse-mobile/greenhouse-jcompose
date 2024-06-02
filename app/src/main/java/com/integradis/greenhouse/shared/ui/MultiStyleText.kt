package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun MultiStyleText(
    firstTextPart: String,
    firstColor: Color,
    secondTextPart: String,
    secondColor: Color,
    typography: TextStyle,
    modifier: Modifier = Modifier,
) {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = firstColor)){
            append(firstTextPart)
        }
        withStyle(style = SpanStyle(color = secondColor)){
            append(secondTextPart)
        }
    },
        style = typography,
        modifier = modifier)
}

@Composable
fun MultiStyleSpacedText(
    firstTextPart: String,
    firstColor: Color,
    secondTextPart: String,
    secondColor: Color,
    typography: TextStyle
) {
    Row {
        Text(
            text = firstTextPart,
            color = firstColor,
            style = typography,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = secondTextPart,
            color = secondColor,
            style = typography,
        )
    }
}
