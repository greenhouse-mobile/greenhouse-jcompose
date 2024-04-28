package com.integradis.greenhouse.feature_main.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integradis.greenhouse.feature_crops_in_progress.ui.CropsInProgress
import com.integradis.greenhouse.feature_dashboard.ui.Dashboard
import com.integradis.greenhouse.feature_mail.ui.Mail
import com.integradis.greenhouse.feature_notification.ui.Notification
import com.integradis.greenhouse.feature_perfil.ui.Perfil
import com.integradis.greenhouse.features_archive.ui.Archives
import com.integradis.greenhouse.shared.ui.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreenhouseMainScreen() {
    val navController = rememberNavController()
    val userName = "Winston Smith"


    Scaffold(
        bottomBar = { NavBar(navController)}
    ) {padding->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()){
            NavHost(navController = navController, startDestination = Routes.Dashboard.route, modifier = Modifier.verticalScroll(rememberScrollState())) {
                composable(route = Routes.Dashboard.route){
                    Dashboard(userName, navController)
                }
                composable(route = Routes.Perfil.route){
                    Perfil()
                }
                composable(route = Routes.Correo.route){
                    Mail()
                }
                composable(route = Routes.CropsInProgress.route) {
                    CropsInProgress()
                }
                composable(route = Routes.Archives.route) {
                    Archives()
                }
                composable(route = Routes.Notification.route) {
                    Notification()
                }
            }

        }
    }

}

sealed class Routes(val route: String) {
    object CropsInProgress : Routes("CropsInProgress")
    object Dashboard : Routes("Dashboard")
    object Perfil : Routes("Perfil")

    object Correo : Routes("Correo")

    object Archives : Routes("Archives")

    object Notification : Routes("Notification")

}