package com.integradis.greenhouse.feature_auth.ui.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.R
import com.integradis.greenhouse.feature_auth.ui.data.remote.AuthService
import com.integradis.greenhouse.feature_auth.ui.data.remote.UserResponse
import com.integradis.greenhouse.feature_auth.ui.data.repositories.AuthRepository
import com.integradis.greenhouse.feature_main.ui.main.Routes
import com.integradis.greenhouse.shared.data.remote.ApiClient
import com.integradis.greenhouse.shared.ui.CustomButton
import com.integradis.greenhouse.shared.ui.CustomTextField
import com.integradis.greenhouse.shared.ui.PasswordTextField
import com.integradis.greenhouse.shared.ui.SideCustomButton

@Composable
fun SignInScreen(
    navController: NavController,
    authRepository: AuthRepository = AuthRepository(ApiClient.getRetrofit().create(AuthService::class.java))
) {

    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold { paddingValues ->

        Image(
            painter = painterResource(id = R.drawable.champis),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Box(
                    modifier = Modifier.padding(paddingValues),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 70.dp, height = 70.dp) // Modifica estos valores según el tamaño deseado
                            .aspectRatio(1.0f))
                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = 15.dp)
                        .background(
                            Color.White,
                            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        ),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(bottom = 280.dp)
                            .padding(16.dp),
                    ) {

                        Row {
                            SideCustomButton(text = "Sign in", onclick = {navController.navigate(Routes.SignIn.route)} ) //lleva a Sign In
                            Spacer(modifier = Modifier.weight(0.1f))
                            SideCustomButton(text = "Sign up", onclick = {navController.navigate(Routes.SignUp.route)}) //lleva a Sign up
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextField(
                            input = username,
                            placeholder = "Username",
                            icon = Icons.Rounded.Person
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        PasswordTextField(
                            input = password,
                            placeholder = "Password"
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        CustomButton( text = "Sign in", onclick = {
                            authRepository.signIn(username.value, password.value) { result ->
                                result.onSuccess { userResponse ->
                                    navController.navigate(Routes.Dashboard.route.replace("{username}", userResponse.username))  // Pasar el username al Dashboard
                                }
                                result.onFailure {
                                    error("Error: ${it.message}")
                                }
                            }
                        })

                    }
                }
            }
        }


    }
}
