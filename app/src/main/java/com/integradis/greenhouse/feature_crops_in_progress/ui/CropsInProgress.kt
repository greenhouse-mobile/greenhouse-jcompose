package com.integradis.greenhouse.feature_crops_in_progress.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.feature_main.ui.main.Routes
import com.integradis.greenhouse.shared.data.repositories.CropRepository
import com.integradis.greenhouse.shared.domain.Crop
import com.integradis.greenhouse.shared.domain.CropPhase
import com.integradis.greenhouse.shared.ui.CropCard
import com.integradis.greenhouse.shared.ui.SearchCropTextField
import com.integradis.greenhouse.ui.theme.Brown
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropsInProgress(
    navController: NavController,
    selectCrop: (Int) -> Unit,
    deleteCrop: (Int) -> Unit,
    cropRepository: CropRepository = CropRepository()
){
    val crops = remember {
        mutableStateOf(emptyList<Crop>())
    }

    val searchCropsInput = remember {
        mutableStateOf("")
    }
    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = System.currentTimeMillis(),
        yearRange = 2000..2024
    )
    val showDatePicker = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf("") }

    cropRepository.getCrops("true") {
        crops.value = it
        Log.d("active", crops.value.toString())
    }
    var newCrop by remember { mutableStateOf(false) }

    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
        Row (
            modifier = Modifier
                .align(Alignment.Start)
                .height(intrinsicSize = IntrinsicSize.Max)
        ) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Go Back",
                    tint = PrimaryGreen40
                )
            }
            Text(
                text = "Go Back",
                color = PrimaryGreen40,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        Text(
            text = "CROPS IN PROGRESS",
            style = Typography.labelLarge,
            color = Color(0xFF465B3F),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row( horizontalArrangement = Arrangement.SpaceBetween){
            SearchCropTextField(
                input = searchCropsInput,
                placeholder = "Search crops...",
            )
            IconButton(
                onClick = {
                    showDatePicker.value = true
                },
            ) {
                Icon(
                    imageVector = Icons.Rounded.Event,
                    contentDescription = "Calendar",
                    tint
                    = PrimaryGreen40)
            }
        }
        Scaffold (floatingActionButton = {
            FloatingActionButton(onClick = { newCrop = true }, containerColor = Brown, contentColor = Color.White) {
                Icon(Icons.Filled.Add, "New crop")
            }
        }){paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                itemsIndexed(crops.value) {index, crop ->
                    if (crop.state == "true"){ // This is useless, the query for this screen already
                        CropCard(               // uses active crops
                            imageUrl = "https://compote.slate.com/images/e4805e57-794c-4d88-b893-c7ac42f604ac.jpeg?width=1200&rect=6480x4320&offset=112x0",
                            crop = crop,
                            navigateTo = {
                                navController.navigate("${Routes.Stepper.route}/${crop.id}")
                            },
                            selectCrop = {
                                selectCrop(index)
                            },
                            deleteCrop = {
                                deleteCrop(index)
                            }
                        )
                    }
                }
            }
            if (newCrop) {
                AlertDialog(
                    onDismissRequest = { newCrop = false },
                    icon = { Icon(Icons.Outlined.Info, contentDescription = "Info Icon", tint = PrimaryGreen40, modifier = Modifier.size(100.dp)) },
                    text = { Text("Are you sure you want to create a crop?") },
                    containerColor = Color.White,
                    confirmButton = {
                        Button(
                            onClick = {
                                newCrop = false
                                val newCrop = Crop(
                                    "cc7c6c19-c416-453a-a93b-99a02fa136d"+(3..100).random().toString(),
                                    "20/11/2021",
                                    phase = CropPhase.STOCK.toString(),
                                    author = "In Progress",
                                    name = "Crop #1",
                                    state = "true")
                                crops.value = crops.value + newCrop
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
                            onClick = { newCrop = false
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
        }
    }

    if (showDatePicker.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker.value = false },
            confirmButton = {
                TextButton(
                    onClick = { showDatePicker.value = false },
                    enabled = datePickerState.selectedDateMillis != null
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker.value = false }) {
                    Text(text = "Dismiss")
                }
            }){
            selectedDate.value = datePickerState.selectedDateMillis.toString()
            DatePicker(state = datePickerState)
        }
    }
}