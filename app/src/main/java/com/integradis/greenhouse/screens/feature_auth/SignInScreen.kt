package com.integradis.greenhouse.screens.feature_auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import com.integradis.greenhouse.R
import com.integradis.greenhouse.model.remote.authentication.AuthService
import com.integradis.greenhouse.network.ApiClient
import com.integradis.greenhouse.repositories.AuthRepository
import com.integradis.greenhouse.screens.feature_main.Routes
import com.integradis.greenhouse.shared.ui.CustomButton
import com.integradis.greenhouse.shared.ui.CustomTextField
import com.integradis.greenhouse.shared.ui.PasswordTextField
import com.integradis.greenhouse.shared.ui.SideCustomButton

@Composable
fun SignInScreen(
    navController: NavController,
    authRepository: AuthRepository = AuthRepository(ApiClient.getRetrofit().create(AuthService::class.java))
) {
    val context = LocalContext.current
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

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Login",
                                color = Color(0xFF67864A), // Color del texto
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
                                    .clickable { navController.navigate(Routes.SignIn.route) },
                                textAlign = TextAlign.Center
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .align(Alignment.BottomCenter)
                                    .background(Color(0xFF67864A))
                            )
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

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF67864A),
                                contentColor = Color.White
                            ),
                            onClick = {
                                if (username.value.isBlank() || password.value.isBlank()) {
                                    Toast.makeText(context, "Username and password must not be empty", Toast.LENGTH_LONG).show()
                                } else {
                                    authRepository.signIn(
                                        username.value,
                                        password.value
                                    ) { result ->
                                        result.fold(
                                            onSuccess = { userResponse ->
                                                navController.navigate(
                                                    Routes.Dashboard.route.replace("{username}", userResponse.username)
                                                ) // Pasar el username al Dashboard
                                            },
                                            onFailure = {
                                                Toast.makeText(
                                                    context, "Error: ${it.message}", Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        )
                                    }
                                }
                        }){
                            Text("Login")
                        }

                    }
                }
            }
        }


    }
}