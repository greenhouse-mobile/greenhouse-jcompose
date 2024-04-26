package com.integradis.greenhouse.feature_crops_in_progress.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.integradis.greenhouse.shared.ui.InputTextField

@Composable
fun CropsInProgress(){
    val hola = remember {
        mutableStateOf("")
    }
    InputTextField(input = hola, placeholder = "Search crops...")
}