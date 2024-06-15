package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.integradis.greenhouse.ui.theme.errorRed

@Composable
fun DeleteItemPopUp(
    onDismissRequest: () -> Unit,
    onClickDismissButton: () -> Unit,
    onConfirmButton: () -> Unit,
    id : String,
    type : String,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        icon = {
            Icon(Icons.Filled.Delete,
                contentDescription = "Delete Icon",
                tint = errorRed,
                modifier = Modifier.size(100.dp))
               },
        text = { Text("Do you want to notify an admin for the deletion of $type $id?") },
        containerColor = Color.White,
        dismissButton = {
            Button(
                onClick = { onClickDismissButton() },
                colors = ButtonDefaults.buttonColors(
                    Color.White
                )
            ) {
                Text("Cancel", color = errorRed)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    //onDeleteClicked()
                    //Aca deberiamos poner la forma en la que se envia mensaje al admin para eliminar el crop
                    onConfirmButton()
                },
                colors = ButtonDefaults.buttonColors(
                    errorRed
                )
            ) {
                Text("Confirm")
            }
        })
}


