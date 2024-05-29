package com.integradis.greenhouse.feature_main.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.integradis.greenhouse.feature_auth.ui.ui.signin.SignInScreen
import com.integradis.greenhouse.feature_auth.ui.ui.signup.SignUpScreen
import com.integradis.greenhouse.feature_company.ui.Company
import com.integradis.greenhouse.feature_crop_records.ui.CropRecords
import com.integradis.greenhouse.feature_crops_in_progress.ui.CropsInProgress
import com.integradis.greenhouse.feature_dashboard.ui.Dashboard
import com.integradis.greenhouse.feature_forgot_password.ui.ForgotPassword
import com.integradis.greenhouse.feature_home.ui.home.HomeScreen
import com.integradis.greenhouse.feature_mail.ui.Mail
import com.integradis.greenhouse.feature_notification.ui.Notification
import com.integradis.greenhouse.feature_perfil.ui.Perfil
import com.integradis.greenhouse.feature_stepper.ui.Stepper
import com.integradis.greenhouse.features_archive.ui.Archives
import com.integradis.greenhouse.shared.domain.Crop
import com.integradis.greenhouse.shared.domain.CropPhase
import com.integradis.greenhouse.shared.ui.CropDetail
import com.integradis.greenhouse.shared.ui.NavBar

@Composable
fun GreenhouseMainScreen() {
    val navController = rememberNavController()
    val name = "Winston Smith"
    val username = "wsmith" //here---------------------------
    val company = "Peru Agro J&V S.A.C."
    val role = "Supervising technician"
    val tin = "8767"

    val crops = remember {
        mutableStateOf(emptyArray<Crop>())
    }

//    val crops = remember {
//        mutableStateListOf(
//            Crop("29", "29/23/2004", CropPhase.PREPARATION_AREA, "In Progress"),
//            Crop("90", "29/14/2004", CropPhase.BUNKER, "In Progress"),
//            Crop("54", "29/23/2004", CropPhase.PREPARATION_AREA, "Done"),
//            Crop("32", "29/14/2004", CropPhase.BUNKER, "Done")
//        )
//    }

    val currentRoute = remember { mutableStateOf("") }
                                                                    
    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute.value = destination.route ?: ""                
    }                                                                   

    Scaffold(
       bottomBar = {                                         
        if (currentRoute.value != Routes.SignIn.route &&  
            currentRoute.value != Routes.SignUp.route &&  
            currentRoute.value != Routes.HomeScreen.route)
        {                                                 
            NavBar(navController)                         
        }                                                 
}                                                     
        
    ) {padding->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()){
            NavHost(navController = navController, startDestination = Routes.HomeScreen.route)  {
                
                composable(route = Routes.HomeScreen.route){
                    HomeScreen(navController = navController)
                }
                
                composable(route = Routes.SignIn.route) {
                    SignInScreen(navController = navController)
                }

                composable(route = Routes.SignUp.route) {
                    SignUpScreen(navController = navController)
                }

                composable(
                    route = Routes.Dashboard.route,
                    arguments = listOf(navArgument(Routes.Dashboard.argument) { type = NavType.StringType })
                ){ backStackEntry ->
                    val username = backStackEntry.arguments?.getString(Routes.Dashboard.argument) ?: ""
                    Dashboard(username, navController)
                }

                composable(route = Routes.Perfil.route){
                    Perfil(navController, name, username, company, role)
                }
                composable(route = Routes.Correo.route){
                    Mail()
                }
                composable(route = Routes.CropsInProgress.route)
                 {
                    CropsInProgress(navController,
                        newCrop = { navController.navigate(Routes.CropDetail.routeWithoutArgument) },
                        selectCrop = {index ->
                            navController.navigate("${Routes.Stepper.route}/${index}")
                        },
                        deleteCrop = {index ->
                            crops.value = crops.value.filterIndexed { idx, _ -> idx != index }.toTypedArray()
                            }
                        )
                }
                composable(route = Routes.Company.route)
                {
                    Company(navController, company, tin)
                }
                composable(
                    route = Routes.Stepper.routeWithArgument,
                    arguments = listOf(navArgument(Routes.Stepper.argument) { type = NavType.StringType})
                ) {backStackEntry ->
                    Stepper(navController = navController, backStackEntry.arguments?.getString("cropId"))
                }
                composable(
                    route = Routes.CropRecords.routeWithArgument,
                    arguments = listOf(
                        navArgument(Routes.CropRecords.firstArgument) { type = NavType.StringType},
                        navArgument(Routes.CropRecords.secondArgument) { type = NavType.StringType}
                        )
                ){backStackEntry ->
                    CropRecords(navController = navController, backStackEntry.arguments?.getString("cropId"),
                        backStackEntry.arguments?.getString("phase"))
                }
                composable(route = Routes.Archives.route) {
                    Archives(
                        navController,
                        crops,
                        selectCrop = {index ->
                            navController.navigate("${Routes.Stepper.route}/${index}")
                        },
                        deleteCrop = {index ->
                            crops.value = crops.value.filterIndexed { idx, _ -> idx != index }.toTypedArray()
                        }
                    )
                }
                composable(Routes.CropDetail.routeWithArgument,
                    arguments = listOf(navArgument(Routes.CropDetail.argument) {
                        type = NavType.IntType
                    })) { backStackEntry ->
                    val index =
                        backStackEntry.arguments?.getInt(Routes.CropDetail.argument) ?: return@composable
                    val contact = if (index < 0) {
                        Crop("", "", phase = CropPhase.STOCK, author = "Alan Galavis", name = "Crop #1", state = "true")
                    } else crops.value[index]
                    CropDetail(
                        navController,
                        saveCrop = {
                            if (index < 0) {
                                crops.value += it
                            } else {
                                crops.value[index] = it
                            }
                        },
                    )
                }
                composable(route = Routes.Notification.route) {
                    Notification()
                }
                composable(route = Routes.ForgotPassword.route) {
                    ForgotPassword(navController,"", "")
                }
            }

        }
    }

}

sealed class Routes(val route: String) {

    object HomeScreen : Routes("HomeScreen")
    object SignIn : Routes("SignIn")
    object SignUp : Routes("SignUp")
    
    object CropsInProgress : Routes("CropsInProgress")
    object Stepper : Routes("Stepper") {
        const val routeWithArgument = "Stepper/{cropId}"
        const val argument = "cropId"
    }
    object CropRecords : Routes("CropRecords") {
        const val routeWithArgument = "CropRecords/{cropId}/{phase}"
        const val firstArgument = "cropId"
        const val secondArgument = "phase"
    }

    object Dashboard : Routes("Dashboard/{username}"){
        const val argument = "username"
    }
    object Perfil : Routes("Perfil")
    object Correo : Routes("Correo")

    object Archives : Routes("Archives")
    object Notification : Routes("Notification")

    object ForgotPassword : Routes("ForgotPassword")

    object Company : Routes("Company")
    object CropDetail : Routes("CropDetail"){
        const val routeWithArgument = "CropDetail/{cropId}"
        const val argument = "cropId"
        const val routeWithoutArgument = "CropDetail/-1"
    } 
}

