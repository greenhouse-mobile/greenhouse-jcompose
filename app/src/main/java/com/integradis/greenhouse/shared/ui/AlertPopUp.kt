package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.integradis.greenhouse.ui.theme.alertGreen

@Composable
fun AlertPopUp(
    onDismissRequest: () -> Unit,
    inlineText: String,
    onClickDismissButton: () -> Unit,
    buttonText: String,
    onConfirmButton: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        icon = {
               Icon(
                   Icons.Outlined.Info,
                   contentDescription = "Information Icon",
                   tint = alertGreen,
                   modifier = Modifier.size(100.dp))
        },
        text = {Text(inlineText)},
        containerColor = Color.White,
        dismissButton = {
            Button(
                onClick = { onClickDismissButton() },
                colors = ButtonDefaults.buttonColors(
                    Color.White
                )
            ) {
                Text("Cancel", color = alertGreen)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmButton()
                },
                colors = ButtonDefaults.buttonColors(
                    alertGreen
                )
            ) {
                Text(buttonText)
            }
        })
}