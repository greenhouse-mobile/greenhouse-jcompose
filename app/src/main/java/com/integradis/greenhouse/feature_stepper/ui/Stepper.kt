package com.integradis.greenhouse.feature_stepper.ui

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.ui.theme.PrimaryGreen40

@Composable
fun Stepper(
    navController : NavController
) {
    Scaffold {paddingValues ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(paddingValues)
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
        }
    }
}