package com.integradis.greenhouse.screens.feature_crop_records

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.factories.CropRecordRepositoryFactory
import com.integradis.greenhouse.model.data.crop_records.CropRecordData
import com.integradis.greenhouse.repositories.CropRecordRepository
import com.integradis.greenhouse.screens.feature_crop_records.ui.CropRecordInputField
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.SubtitleCropList
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun ModifyRecordScreen(
    navController: NavController,
    recordId: String,
    sharedPreferencesHelper: SharedPreferencesHelper
) {
    val cropRecordRepository = CropRecordRepositoryFactory.getRecordRepository(sharedPreferencesHelper)

    val cropRecordDatum = remember {
        mutableStateOf<CropRecordData?>(null)
    }

    val formState = remember { mutableStateListOf<MutableMap<String?, String?>>() }

    cropRecordRepository.getRecordById(recordId){
        cropRecordDatum.value = it
        Log.d("ModifyRecordScreen ", cropRecordDatum.value.toString());
        for (phaseDataEntry in cropRecordDatum.value?.phaseData?.data!!) {
            formState.add(mutableMapOf(phaseDataEntry["name"] to phaseDataEntry["value"]))

        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
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
        Text(
            text = "New Record ID: 1",
            style = Typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF465B3F),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        formState.forEach {map ->
            map["name"]?.let {name ->
                map["value"]?.let { value ->
                    CropRecordInputField(
                        title = name,
                        content = value,
                        onValueChange = {
                            map["value"] = it
                        },
                        isEmpty = false
                    )
                }
            }
        }

        // Chécate esta locura
        /*cropRecordDatum.value?.phaseData?.data?.zip(recordInputField)?.forEach { pair ->
            pair.first["name"]?.let { name ->
                pair.first["value"]?.let { data ->
                    CropRecordInputField(
                        title = name,
                        content = pair.second,
                        isEmpty = false
                    )
                }
            }
        }*/
     }
}