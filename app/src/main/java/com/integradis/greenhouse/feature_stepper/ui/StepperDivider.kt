package com.integradis.greenhouse.feature_stepper.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StepperDivider() {
    VerticalDivider(
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, bottom = 5.dp)
            .height(40.dp),
    )
}