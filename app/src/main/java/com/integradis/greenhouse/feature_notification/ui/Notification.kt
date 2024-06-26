package com.integradis.greenhouse.feature_notification.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notification(){
    val rem = remember {
        mutableStateOf("")
    }
    Text(
        text= "Vista Notificaciones",
        style = Typography.labelLarge,
        color = PrimaryGreen40,
    )
}