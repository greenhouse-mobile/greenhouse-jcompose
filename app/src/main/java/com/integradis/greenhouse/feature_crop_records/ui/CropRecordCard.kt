package com.integradis.greenhouse.feature_crop_records.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Yard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.integradis.greenhouse.shared.domain.CropRecordData
import com.integradis.greenhouse.shared.ui.MultiStyleSpacedText
import com.integradis.greenhouse.shared.ui.MultiStyleText
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun CropRecordCard(
    modifier : Modifier = Modifier
) {
    val cropData = CropRecordData(id = "127", author = "Alan Galavis", cropDay = "1", "22/06/2024 12:14")
    // Provisional information for testing
    val placeholderCropData = listOf(
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
    )
    var expandedState by remember {
        mutableStateOf(false)
    }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clickable { expandedState = !expandedState },

    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Record ID: ID - #${cropData.id}",
                    style = Typography.labelLarge,
                    color = PrimaryGreen40,
                    modifier = Modifier.weight(1f).padding(start = 3.dp)
                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.height(20.dp).width(20.dp)
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete Crop Record",
                        tint = Color.Red,
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.height(20.dp).width(20.dp)
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Edit Crop Record",
                        tint = Color.Gray,

                    )
                }
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = "Author Icon",
                    tint = PrimaryGreen40
                )
                MultiStyleText(
                    firstTextPart = "Author: ",
                    firstColor = Color.Black,
                    secondTextPart = cropData.author,
                    secondColor = Color.Gray,
                    typography = Typography.labelMedium,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.AccessTime,
                    contentDescription = "Crop Day Icon",
                    tint = PrimaryGreen40
                )
                MultiStyleText(
                    firstTextPart = "Day: ",
                    firstColor = Color.Black,
                    secondTextPart = cropData.cropDay,
                    secondColor = Color.Gray,
                    typography = Typography.labelMedium,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Outlined.Yard,
                    contentDescription = "Entry Date Icon",
                    tint = PrimaryGreen40
                )
                MultiStyleText(
                    firstTextPart = "Entry Date: ",
                    firstColor = Color.Black,
                    secondTextPart = cropData.entryDate,
                    secondColor = Color.Gray,
                    typography = Typography.labelMedium,
                    modifier = Modifier.weight(1f).padding(start = 5.dp)
                )
                if (!expandedState) {
                    Text(
                        text = "See more",
                        style = Typography.labelMedium,
                        color = Color.Gray,
                    )
                }
            }
            if (expandedState) {
                Text("")
                for (placeholderCropDatum in placeholderCropData) {
                    placeholderCropDatum["name"]?.let { name ->
                        placeholderCropDatum["value"]?.let { data ->
                            MultiStyleSpacedText(
                                firstTextPart = name,
                                firstColor = Color.Black,
                                secondTextPart = data,
                                secondColor = Color.Gray,
                                typography = Typography.labelMedium,
                            )
                        }
                    }
                }
            }
        }
    }
}