package com.integradis.greenhouse.screens.feature_main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.integradis.greenhouse.feature_mail.ui.Mail
import com.integradis.greenhouse.feature_notification.ui.Notification
import com.integradis.greenhouse.screens.feature_auth.ForgotPassword
import com.integradis.greenhouse.screens.feature_auth.SignInScreen
import com.integradis.greenhouse.screens.feature_authentication_home.AuthenticationHomeScreen
import com.integradis.greenhouse.screens.feature_company.CompanyScreen
import com.integradis.greenhouse.screens.feature_crop_records.CropRecordsScreen
import com.integradis.greenhouse.screens.feature_crop_records.ModifyRecordScreen
import com.integradis.greenhouse.screens.feature_crops_archive.CropsArchivesScreen
import com.integradis.greenhouse.screens.feature_crops_in_progress.CropsInProgressScreen
import com.integradis.greenhouse.screens.feature_dashboard.DashboardScreen
import com.integradis.greenhouse.screens.feature_profile.ProfileScreen
import com.integradis.greenhouse.screens.feature_stepper.Stepper
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import com.integradis.greenhouse.shared.ui.Layout

@Composable
fun GreenhouseMainScreen(sharedPreferencesHelper1: SharedPreferencesHelper) {
    val name = "Winston Smith"
    val company = "Peru Agro J&V S.A.C."
    val tin = "8767"

    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val currentRoute = remember { mutableStateOf("") }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute.value = destination.route ?: ""
    }

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(route = Routes.HomeScreen.route) {
            AuthenticationHomeScreen(navController = navController)
        }

        composable(route = Routes.SignIn.route) {
            SignInScreen(
                navController = navController,
                sharedPreferencesHelper = sharedPreferencesHelper
            )
        }

        composable(route = Routes.Dashboard.route) {
            Layout(navController = navController) {
                DashboardScreen(
                    username = name,
                    navController = navController
                ) //sharedPreferencesHelper = sharedPreferencesHelper
            }
        }

        composable(route = Routes.Perfil.route) {
            Layout(navController = navController) {
                ProfileScreen(
                    navController,
                    company,
                    sharedPreferencesHelper = sharedPreferencesHelper
                )
            }
        }

        composable(route = Routes.Correo.route) {
            Layout(navController = navController) {
                Mail()
            }
        }

        composable(
            route = Routes.CropsInProgress.route
        )
        {
            Layout(navController = navController) {
                CropsInProgressScreen(navController,
                    sharedPreferencesHelper = sharedPreferencesHelper,
                    selectCrop = { index ->
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
                CompanyScreen(navController, company, tin)
            }
        }

        composable(
            route = Routes.Stepper.routeWithArgument,
            arguments = listOf(navArgument(Routes.Stepper.argument) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            Layout(navController = navController) {
                backStackEntry.arguments?.getString("cropId")?.let {
                    Stepper(
                        navController = navController,
                        it,
                        sharedPreferencesHelper = sharedPreferencesHelper
                    )
                }
            }
        }


        composable(
            route = Routes.ModifyRecords.routeWithArgument,
            arguments = listOf(
                navArgument(Routes.ModifyRecords.firstArgument) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            Layout(navController = navController) {
                backStackEntry.arguments?.getString("recordId")?.let {
                    ModifyRecordScreen(
                        navController = navController,
                        it,
                        sharedPreferencesHelper
                    )
                }
            }
        }

        composable(
            route = Routes.CropRecords.route,
        ) {
            Layout(navController = navController) {
                CropRecordsScreen(
                    navController = navController,
                    sharedPreferencesHelper
                )
            }
        }

        composable(route = Routes.Archives.route) {
            Layout(navController = navController) {
                CropsArchivesScreen(
                    navController,
                    sharedPreferencesHelper = sharedPreferencesHelper,
                    selectCrop = { index ->
                        navController.navigate("${Routes.Stepper.route}/${index}")
                    },
                    deleteCrop = {
//                                index ->
//                                crops.value = crops.value.filterIndexed { idx, _ -> idx != index }.toTypedArray()
                    }
                )
            }
        }

        composable(route = Routes.Notification.route) {
            Layout(navController = navController) {
                Notification()
            }
        }

        composable(route = Routes.ForgotPassword.route) {
            Layout(navController = navController) {
                ForgotPassword(navController, "", "")
            }
        }
    }

}


sealed class Routes(val route: String) {

    object HomeScreen : Routes("HomeScreen")
    object SignIn : Routes("SignIn")

    object CropsInProgress : Routes("CropsInProgress")
    object Stepper : Routes("Stepper") {
        const val routeWithArgument = "Stepper/{cropId}"
        const val argument = "cropId"
    }

    object CropRecords : Routes("CropRecords")

    object ModifyRecords : Routes("ModifyRecords"){
        const val routeWithArgument = "ModifyRecords/{recordId}"
        const val firstArgument = "recordId"
    }

    object Dashboard : Routes("Dashboard"){
        const val routeWithArgument = "Dashboard/{username}"
        const val argument = "username"
    }
    object Perfil : Routes("Perfil")
    object Correo : Routes("Correo")

    object Archives : Routes("Archives")
    object Notification : Routes("Notification")

    object ForgotPassword : Routes("ForgotPassword")

    object Company : Routes("Company")

}
