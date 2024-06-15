package com.integradis.greenhouse.screens.feature_stepper

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.screens.feature_main.Routes
import com.integradis.greenhouse.screens.feature_stepper.ui.StepperButton
import com.integradis.greenhouse.screens.feature_stepper.ui.StepperDivider
import com.integradis.greenhouse.repositories.CropRepository
import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.model.data.crops.CropPhase
import com.integradis.greenhouse.shared.ui.MultiStyleText
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun Stepper(
    navController: NavController,
    cropId: String,
    cropRepository: CropRepository = CropRepository()
) {

    val crop = remember {
        mutableStateOf<Crop?>(null)
    }

    cropRepository.getCropById(cropId) {
        crop.value = it
    }

    Log.d("STATE", crop.value.toString())

    val itemsList = mutableListOf(
        CropPhase.STOCK, CropPhase.PREPARATION_AREA,
        CropPhase.BUNKER, CropPhase.TUNNEL, CropPhase.INCUBATION, CropPhase.CASING,
        CropPhase.INDUCTION, CropPhase.HARVEST)

    Scaffold {paddingValues ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
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
                text = "STEPPER",
                style = Typography.labelLarge,
                color = Color(0xFF465B3F),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            MultiStyleText(
                firstTextPart = "Crop ID: ",
                firstColor = Color.Black,
                secondTextPart = "ID - #127",
                secondColor = Color.Gray,
                typography = Typography.labelMedium,
            )
            MultiStyleText(
                firstTextPart = "Start Date: ",
                firstColor = Color.Black,
                secondTextPart = "22/05/2024",
                secondColor = Color.Gray,
                typography = Typography.labelMedium,
                modifier = Modifier.padding(top = 20.dp)
            )
            Scaffold(
                modifier = Modifier.padding(start = 50.dp, top = 30.dp, bottom = 18.dp)
            ) {paddingValues ->
                LazyColumn (
                    modifier = Modifier.padding(paddingValues)
                ) {
                    items(itemsList) {item ->
                        if (item < CropPhase.getValueOf(crop.value?.phase)){
                            StepperButton(
                                phase = item,
                                isComplete = true,
                                navigateTo = {
                                    navController.navigate("${Routes.CropRecords.route}/${cropId}/${item.getPhaseName()}")
                                })
                        }
                        else if (item == CropPhase.getValueOf(crop.value?.phase)) {
                            StepperButton(
                                phase = item,
                                isCurrent = true,
                                navigateTo = {
                                    navController.navigate("${Routes.CropRecords.route}/${cropId}/${item.getPhaseName()}")
                                })
                        }
                        else {
                            StepperButton(
                                phase = item,
                                navigateTo = {
                                    navController.navigate("${Routes.CropRecords.route}/${cropId}/${item.getPhaseName()}")
                                })
                        }
                        if(item != CropPhase.HARVEST) StepperDivider()

                    }
                }
            }
        }
    }
}