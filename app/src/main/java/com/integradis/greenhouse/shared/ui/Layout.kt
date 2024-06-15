package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.integradis.greenhouse.shared.ui.NavBar

@Composable
fun Layout(
    navController: NavHostController,
    currentScreen: @Composable () -> Unit
){


//    val crops = remember {
//        mutableStateListOf(
//            Crop("29", "29/23/2004", CropPhase.PREPARATION_AREA, "In Progress"),
//            Crop("90", "29/14/2004", CropPhase.BUNKER, "In Progress"),
//            Crop("54", "29/23/2004", CropPhase.PREPARATION_AREA, "Done"),
//            Crop("32", "29/14/2004", CropPhase.BUNKER, "Done")
//        )
//    }

    

    Scaffold(
        bottomBar = {
            NavBar(navController)
        }

    ) {padding->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()){
                currentScreen()
        }
    }
}