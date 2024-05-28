package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.shared.domain.Crop
import com.integradis.greenhouse.shared.domain.CropPhase
import com.integradis.greenhouse.ui.theme.Brown
import com.integradis.greenhouse.ui.theme.PrimaryGreen40

@Composable
fun CropDetail(
    navController: NavController,
    saveCrop: (Crop) -> Unit,
    crop: Crop? = null
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            icon = { Icon(Icons.Outlined.Info, contentDescription = "Info Icon", tint = PrimaryGreen40, modifier = Modifier.size(100.dp)) },
            text = { Text("Are you sure you want to create a crop?") },
            containerColor = Color.White,
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        val newCrop = Crop((1..100).random().toString(), "20/11/2021", phase = CropPhase.STOCK, author = "In Progress", name = "Crop #1", state = "true")
                        saveCrop(newCrop)
                        navController.navigateUp()
                    },
                    colors = ButtonDefaults.buttonColors(
                        PrimaryGreen40
                    )
                ) {
                    Text("Confirm", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false
                        navController.navigateUp()},
                    colors = ButtonDefaults.buttonColors(
                        Color.White
                    )
                ) {
                    Text("Cancel", color = PrimaryGreen40)
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .paddingFromBaseline(64.dp)
            .onGloballyPositioned { /* Do something if needed */ }
            .clickable { showDialog = true }
    ) {
        Text(
            text = "Save",
            color = Color.White
        )
    }
}