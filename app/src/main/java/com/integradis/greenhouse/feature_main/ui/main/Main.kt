package com.integradis.greenhouse.feature_main.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integradis.greenhouse.feature_crops_in_progress.ui.CropsInProgress

@Composable
fun GreenhouseMainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.CropsInProgress.route) {
        composable(route = Routes.CropsInProgress.route) {
            CropsInProgress()
        }
    }

}

sealed class Routes(val route: String) {
    object CropsInProgress : Routes("CropsInProgress")
}