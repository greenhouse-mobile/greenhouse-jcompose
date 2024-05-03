package com.integradis.greenhouse.feature_crop_records.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.shared.domain.CropRecordData
import com.integradis.greenhouse.shared.ui.SearchCropTextField
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.SubtitleCropList
import com.integradis.greenhouse.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CropRecords(
    navController : NavController,
    cropId : String?, //Data to search in API
    phase : String?
) {
    val searchRecordsInput = remember {
        mutableStateOf("")
    }
    //Placeholder data
    val cropData = mutableListOf(CropRecordData(id = "127",
        author = "Alan Galavis",
        cropDay = "1",
        entryDate = "22/06/2024 12:14",
        phaseData = listOf(
            mapOf(
                "name" to "Hay",
                "value" to "128"
            ),
            mapOf(
                "name" to "Corn",
                "value" to "300"
            ),
            mapOf(
                "name" to "Guano",
                "value" to "100"
            ),
            mapOf(
                "name" to "Cotton seed cake",
                "value" to "400"
            ),
            mapOf(
                "name" to "Soybean meal",
                "value" to "356"
            ),
            mapOf(
                "name" to "Urea",
                "value" to "356"
            ),
            mapOf(
                "name" to "Ammonium sulfate",
                "value" to "125"
            ),
        )),
        CropRecordData(id = "127",
            author = "Max Soto",
            cropDay = "4",
            entryDate = "27/06/2024 12:14",
            phaseData = listOf(
                mapOf(
                    "name" to "Hay",
                    "value" to "90"
                ),
                mapOf(
                    "name" to "Corn",
                    "value" to "180"
                ),
                mapOf(
                    "name" to "Guano",
                    "value" to "20"
                ),
                mapOf(
                    "name" to "Cotton seed cake",
                    "value" to "300"
                ),
                mapOf(
                    "name" to "Soybean meal",
                    "value" to "256"
                ),
                mapOf(
                    "name" to "Urea",
                    "value" to "156"
                ),
                mapOf(
                    "name" to "Ammonium sulfate",
                    "value" to "125"
                ),
            )),
        CropRecordData(id = "127",
            author = "Andres Soto",
            cropDay = "9",
            entryDate = "AYUDAA",
            phaseData = listOf(
                mapOf(
                    "name" to "Hay",
                    "value" to "90"
                ),
                mapOf(
                    "name" to "Corn",
                    "value" to "180"
                ),
                mapOf(
                    "name" to "Guano",
                    "value" to "20"
                ),
                mapOf(
                    "name" to "Cotton seed cake",
                    "value" to "300"
                ),
                mapOf(
                    "name" to "Soybean meal",
                    "value" to "256"
                ),
                mapOf(
                    "name" to "Urea",
                    "value" to "156"
                ),
                mapOf(
                    "name" to "Ammonium sulfate",
                    "value" to "125"
                ),
            )),

    )
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
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
        // Change for crop.id
        Text(
            text = "Crop ID: ID - #127",
            style = Typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF465B3F),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Preparation area",
            style = Typography.labelMedium,
            color = SubtitleCropList,
        )
        Row(
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        ) {
            SearchCropTextField(
                input = searchRecordsInput,
                placeholder = "Search crops...",
                modifier = Modifier.width(210.dp)
            )
            ElevatedCard(
                modifier = Modifier.padding(top = 4.dp, start = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                ),
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.Download,
                        contentDescription = "Download Crop Records"
                    )
                }
            }
            ElevatedCard(
                modifier = Modifier.padding(top = 4.dp, start = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                ),
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Rounded.Event,
                        contentDescription = "Filter Records"
                    )
                }
            }
        }
        Scaffold {paddingValues ->  
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(cropData) {cropDatum ->
                    CropRecordCard(
                        cropRecordData = cropDatum)
                }
            }
        }
    }
}