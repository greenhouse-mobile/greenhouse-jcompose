package com.integradis.greenhouse.feature_stepper.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.integradis.greenhouse.shared.domain.CropPhase
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.SecondaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun StepperButton(
    phase: CropPhase,
    isComplete: Boolean = false,
    isCurrent: Boolean = false,
) {
    if (isCurrent){
        Row() {
            Button(
                onClick = {
                    /*TODO*/
                },
                modifier = Modifier.size(40.dp),
                border = BorderStroke(1.dp, SecondaryGreen40),
                colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen40),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = phase.getPhaseNumber(),
                    style = Typography.labelSmall,
                    color = Color.White
                )
            }
            Text(
                text = phase.getPhaseName(),
                style = Typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen40,
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
                    .clickable { },
            )
        }
    }
    else {
        if(isComplete) {
            Row() {
                Button(
                    onClick = {
                        /*TODO*/
                    },
                    modifier = Modifier.size(40.dp),
                    border = BorderStroke(1.dp, SecondaryGreen40),
                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen40),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Completed Crop Phase"
                    )
                }
                Text(
                    text = phase.getPhaseName(),
                    style = Typography.labelMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .clickable { },
                )
            }
        }
        else {
            Row() {
                OutlinedButton(
                    onClick = {
                        /*TODO*/
                    },
                    modifier = Modifier.size(40.dp),
                    border = BorderStroke(1.dp, SecondaryGreen40),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(
                        text = phase.getPhaseNumber(),
                        style = Typography.labelSmall,
                        color = PrimaryGreen40
                    )
                }
                Text(
                    text = phase.getPhaseName(),
                    style = Typography.labelMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .clickable { },
                )
            }
        }
    }

}