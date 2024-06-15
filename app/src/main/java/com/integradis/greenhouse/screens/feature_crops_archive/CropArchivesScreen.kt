package com.integradis.greenhouse.screens.feature_crops_archive

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.screens.feature_main.Routes
import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.shared.ui.CropCard
import com.integradis.greenhouse.shared.ui.SearchCropTextField
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropsArchivesScreen(
    navController: NavController,
    crops: MutableState<Array<Crop>>,
    selectCrop: (Int) -> Unit,
    deleteCrop: (Int) -> Unit
){
    val searchCropsInput = remember {
        mutableStateOf("")
    }
    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = System.currentTimeMillis(),
        yearRange = 2000..2024
    )
    val showDatePicker = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf("") }

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
            text = "CROPS ARCHIVE",
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
        Scaffold {paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                itemsIndexed(crops.value) {index, crop ->
                    if (crop.state == "Done"){
                        CropCard(
                            imageUrl = "https://i.pinimg.com/originals/fd/65/01/fd6501a1ed1fc18cb4685c8f69bb4df3.jpg",
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