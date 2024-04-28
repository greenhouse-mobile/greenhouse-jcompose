package com.integradis.greenhouse.feature_main.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.integradis.greenhouse.feature_auth.ui.signin.SignInScreen
import com.integradis.greenhouse.feature_auth.ui.signup.SignUpScreen
import com.integradis.greenhouse.feature_crops_in_progress.ui.CropsInProgress
import com.integradis.greenhouse.feature_dashboard.ui.Dashboard
import com.integradis.greenhouse.feature_forgot_password.ui.ForgotPassword
import com.integradis.greenhouse.feature_home.ui.home.HomeScreen
import com.integradis.greenhouse.feature_mail.ui.Mail
import com.integradis.greenhouse.feature_notification.ui.Notification
import com.integradis.greenhouse.feature_perfil.ui.Perfil
import com.integradis.greenhouse.feature_stepper.ui.Stepper
import com.integradis.greenhouse.features_archive.ui.Archives
import com.integradis.greenhouse.shared.ui.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreenhouseMainScreen() {
    val navController = rememberNavController()
    val name = "Winston Smith"
    val username = "wsmith"
    val company = "Peru Agro J&V S.A.C."
    val role = "Supervising technician"


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
                    Dashboard(name, navController)
                }
                composable(route = Routes.Perfil.route){
                    Perfil(navController, name, username, company, role)
                }
                composable(route = Routes.Correo.route){
                    Mail()
                }
                composable(route = Routes.CropsInProgress.route)
                 {
                    CropsInProgress(navController)
                }
                composable(
                    route = Routes.Stepper.routeWithArgument,
                    arguments = listOf(navArgument(Routes.Stepper.argument) { type = NavType.StringType})
                ) {backStackEntry ->
                    Stepper(navController = navController, backStackEntry.arguments?.getString("cropId"))
                }
                composable(route = Routes.Archives.route) {
                    Archives()
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
    object Dashboard : Routes("Dashboard")
    object Perfil : Routes("Perfil")
    object Correo : Routes("Correo")

    object Archives : Routes("Archives")

    object Notification : Routes("Notification")

    object ForgotPassword : Routes("ForgotPassword")

}

