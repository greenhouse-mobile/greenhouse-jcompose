package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideCustomButton(text: String, onclick: () -> Unit) {
    Button(
        onClick = { onclick() },
        modifier = Modifier
            .widthIn(min = 0.dp, max = 400.dp) // Ancho máximo para evitar que ocupe demasiado
    ) {
        Text(text = text)
    }
}