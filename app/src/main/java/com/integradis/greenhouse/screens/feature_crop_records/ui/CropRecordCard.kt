package com.integradis.greenhouse.screens.feature_crop_records.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhotoAlbum
import androidx.compose.material.icons.outlined.Yard
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.model.data.crop_records.CropRecordResponse
import com.integradis.greenhouse.screens.feature_main.Routes
import com.integradis.greenhouse.shared.ui.AlertPopUp
import com.integradis.greenhouse.shared.ui.DeleteItemPopUp
import com.integradis.greenhouse.shared.ui.MultiStyleSpacedText
import com.integradis.greenhouse.shared.ui.MultiStyleText
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography
import com.integradis.greenhouse.ui.theme.errorRed
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CropRecordCard(
    cropRecordResponse: CropRecordResponse,
    navController: NavController,
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    // Provisional information for testing
    var expandedState by remember {
        mutableStateOf(false)
    }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clickable {
                expandedState = !expandedState
                coroutineScope.launch {
                    bringIntoViewRequester.bringIntoView()
                }
            },

    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showDeleteDialog){
                    DeleteItemPopUp(
                        onDismissRequest = { showDeleteDialog = false },
                        onClickDismissButton = { showDeleteDialog = false },
                        onConfirmButton = { showDeleteDialog = false },
                        id = cropRecordResponse.id,
                        type = "record"
                    )
                }
                if (showEditDialog){
                    AlertPopUp(
                        onDismissRequest = { showEditDialog = false },
                        inlineText = "Do you want to notify an admin for the editing of " +
                                "record ${cropRecordResponse.id}?",
                        onClickDismissButton = { showEditDialog = false },
                        buttonText = "Yes, notify",
                        onConfirmButton = {
                            navController.navigate("${Routes.ModifyRecords.route}/${cropRecordResponse.id}")
                            showEditDialog = false
                        }
                    )
                }
                Text(
                    text = "Record ID: ID - #${cropRecordResponse.id}",
                    style = Typography.labelLarge,
                    color = PrimaryGreen40,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 3.dp)
                )
                IconButton(
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete Crop Record",
                        tint = errorRed,
                    )
                }
                IconButton(
                    onClick = { showEditDialog = true   },
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
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
                    secondTextPart = /*CropRecordResponse.author*/ "123",
                    secondColor = Color.Gray,
                    typography = Typography.labelMedium,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.PhotoAlbum,
                    contentDescription = "Phase Icon",
                    tint = PrimaryGreen40
                )
                MultiStyleText(
                    firstTextPart = "Phase: ",
                    firstColor = Color.Black,
                    secondTextPart = cropRecordResponse.phase ,
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
                    secondTextPart = cropRecordResponse.createdDate,
                    secondColor = Color.Gray,
                    typography = Typography.labelMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp)
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
                for (phaseDataEntry in cropRecordResponse.phaseData.data) {
                    phaseDataEntry["name"]?.let { name ->
                        phaseDataEntry["value"]?.let { data ->
                            MultiStyleSpacedText(
                                firstTextPart = name,
                                firstColor = Color.Gray,
                                secondTextPart = data,
                                secondColor = Color.Black,
                                typography = Typography.labelMedium,
                            )
                        }
                    }
                }
            }
        }
    }
}