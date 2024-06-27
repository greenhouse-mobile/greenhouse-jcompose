package com.integradis.greenhouse.screens.feature_crop_records

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.factories.CropRecordRepositoryFactory
import com.integradis.greenhouse.model.data.crop_records.CropRecordData
import com.integradis.greenhouse.repositories.CropRecordRepository
import com.integradis.greenhouse.screens.feature_crop_records.ui.CropRecordCard
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import com.integradis.greenhouse.shared.ui.AlertPopUp
import com.integradis.greenhouse.shared.ui.SearchCropTextField
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.SubtitleCropList
import com.integradis.greenhouse.ui.theme.Typography
import com.integradis.greenhouse.ui.theme.buttonBrown
import com.integradis.greenhouse.ui.theme.errorRed

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CropRecordsScreen(
    navController : NavController,
    cropId : String?, //Data to search in API
    phase : String?,
    sharedPreferencesHelper: SharedPreferencesHelper
) {

    val cropRecordRepository = CropRecordRepositoryFactory.getRecordRepository(sharedPreferencesHelper)

    val cropDataReal = remember {
        mutableStateOf(emptyList<CropRecordData>())
    }
    val searchRecordsInput = remember {
        mutableStateOf("")
    }

    var showEndPhaseDialog by remember { mutableStateOf(false) }

    cropId?.let {
        phase?.let { cropPhase ->
            cropRecordRepository.getCropRecords(it, cropPhase) {
                Log.d("Phase: ", cropPhase)
            cropDataReal.value = it
            }
        }
    }

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
        if(showEndPhaseDialog){
            AlertPopUp(
                onDismissRequest = { showEndPhaseDialog = false },
                inlineText = "Once this phase is over, " +
                        "you will not be able to add new records. Are you sure you want to continue?",
                onClickDismissButton = { showEndPhaseDialog = false },
                buttonText = "Yes, end phase",
                onConfirmButton = { showEndPhaseDialog = false }
            )
        }
        Scaffold(
            floatingActionButton = { Row() {
                FloatingActionButton(
                    onClick = { showEndPhaseDialog = true },
                    modifier = Modifier.offset(x= (-200).dp),
                    shape = RoundedCornerShape(30.dp),
                    containerColor = errorRed,
                    contentColor = Color.White
                ) {
                    Text(
                        "End phase",
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        fontWeight = FontWeight.Bold

                    )
                }
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    containerColor = buttonBrown,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, "Add New Record")
                }
            }
            }) { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(cropDataReal.value){cropDatum ->
                    Log.d("CropRecord: ", cropDatum.toString())
                    CropRecordCard(
                        cropRecordData = cropDatum,
                        navController = navController)
                }
            }
        }
    }
}