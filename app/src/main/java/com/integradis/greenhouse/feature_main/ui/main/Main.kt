package com.integradis.greenhouse.feature_main.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun GreenhouseMainScreen() {
    val navController = rememberNavController()
    val name = "Winston Smith"
    val username = "wsmith" //here---------------------------
    val company = "Peru Agro J&V S.A.C."
    val role = "Supervising technician"
    val tin = "8767"


    NavHost(navController = navController, startDestination = Routes.Dashboard.route, )  {

        composable(route = Routes.HomeScreen.route){
            HomeScreen(navController = navController)
        }

        composable(route = Routes.SignIn.route) {
            SignInScreen(
                navigateToSignUp = {navController.navigate(Routes.SignUp.route)},
                navigateToSignIn = {navController.navigate(Routes.SignIn.route)},
                navigateToDashboard = {navController.navigate(Routes.Dashboard.route)}
            )
        }

        composable(route = Routes.SignUp.route) {
            SignUpScreen(
                navigateToSignUp = {navController.navigate(Routes.SignUp.route)},
                navigateToSignIn = {navController.navigate(Routes.SignIn.route)},
                navigateToDashboard = {navController.navigate(Routes.Dashboard.route)}
            )
        }

        composable(route = Routes.Dashboard.route){
            Layout(navController = navController) {
                Dashboard(
                    name = name,
                    navController = navController
                )
            }
        }

        composable(route = Routes.Perfil.route){
            Layout(navController = navController) {
                Perfil(navController, name, username, company, role)
            }
        }

        composable(route = Routes.Correo.route){
            Layout(navController = navController) {
                Mail()
            }
        }

        composable(
            route = Routes.CropsInProgress.route,
        )
        {
            Layout(navController = navController) {
                CropsInProgress(navController,
                    selectCrop = {index ->
                        navController.navigate("${Routes.Stepper.route}/${index}")
                    },
                    deleteCrop = {
//                        index ->
//                        crops.value = crops.value.filterIndexed { idx, _ -> idx != index }.toTypedArray()
                    }
                )
            }
        }

        composable(route = Routes.Company.route)
        {
            Layout(navController = navController) {
                Company(navController, company, tin)
            }
        }

        composable(
            route = Routes.Stepper.routeWithArgument,
            arguments = listOf(navArgument(Routes.Stepper.argument) { type = NavType.StringType})
        ) {backStackEntry ->
            Layout(navController = navController) {
                Stepper(navController = navController, backStackEntry.arguments?.getString("cropId"))
            }
        }

        composable(
            route = Routes.CropRecords.routeWithArgument,
            arguments = listOf(
                navArgument(Routes.CropRecords.firstArgument) { type = NavType.StringType},
                navArgument(Routes.CropRecords.secondArgument) { type = NavType.StringType}
            )
        ){backStackEntry ->
            Layout(navController = navController) {
                CropRecords(navController = navController, backStackEntry.arguments?.getString("cropId"),
                    backStackEntry.arguments?.getString("phase"))
            }
        }

        composable(route = Routes.Archives.route) {
            Layout(navController = navController) {
//                Archives(
//                    navController,
//                    crops,
//                    selectCrop = {index ->
//                        navController.navigate("${Routes.Stepper.route}/${index}")
//                    },
//                    deleteCrop = {index ->
//                        crops.value = crops.value.filterIndexed { idx, _ -> idx != index }.toTypedArray()
//                    }
//                )
            }
        }

        composable(route = Routes.Notification.route) {
            Layout(navController = navController) {
                Notification()
            }
        }

        composable(route = Routes.ForgotPassword.route) {
            Layout(navController = navController) {
                ForgotPassword(navController,"", "")
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

}

