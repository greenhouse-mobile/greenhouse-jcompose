package com.integradis.greenhouse.feature_auth.ui.ui.signup

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
import androidx.compose.material.icons.rounded.Business
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.integradis.greenhouse.feature_auth.ui.data.remote.SignUpRequest
import com.integradis.greenhouse.feature_auth.ui.data.repositories.AuthRepository
import com.integradis.greenhouse.feature_main.ui.main.Routes
import com.integradis.greenhouse.shared.data.remote.ApiClient
import com.integradis.greenhouse.shared.ui.CustomButton
import com.integradis.greenhouse.shared.ui.CustomTextField
import com.integradis.greenhouse.shared.ui.PasswordTextField
import com.integradis.greenhouse.shared.ui.SideCustomButton

@Composable
fun SignUpScreen(
    navController: NavController,
    authRepository: AuthRepository = AuthRepository(ApiClient.getRetrofit().create(AuthService::class.java))
) {
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

        ){

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ){

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
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(16.dp),
                    ) {

                        Row (modifier = Modifier.fillMaxWidth().padding(paddingValues),
                        ){
                            SideCustomButton(text = "Sign in", onclick = {navController.navigate(Routes.SignIn.route)} ) //lleva a Sign In
                            Spacer(modifier = Modifier.weight(0.1f))
                            SideCustomButton(text = "Sign up", onclick = {navController.navigate(Routes.SignUp.route)} ) //lleva a Sign up
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        val businessName = remember {
                            mutableStateOf("")
                        }
                        val TIN = remember {
                            mutableStateOf("")
                        }
                        val firstName = remember {
                            mutableStateOf("")
                        }
                        val lastName = remember {
                            mutableStateOf("")
                        }
                        val username = remember {
                            mutableStateOf("")
                        }
                        val password = remember {
                            mutableStateOf("")
                        }
                        val acceptedTerms = remember { mutableStateOf(false) }

                        CustomTextField(input = businessName, placeholder = "Business Name", icon = Icons.Rounded.Business)
                        Spacer(modifier = Modifier.height(4.dp))
                        CustomTextField(input = TIN, placeholder = "TIN", icon = Icons.Rounded.Business)
                        Spacer(modifier = Modifier.height(4.dp))
                        CustomTextField(input = firstName, placeholder = "First name of the registrant", icon = Icons.Rounded.Person)
                        Spacer(modifier = Modifier.height(4.dp))
                        CustomTextField(input = lastName, placeholder = "Last Name of the registrant", icon = Icons.Rounded.Person)
                        Spacer(modifier = Modifier.height(4.dp))
                        CustomTextField(input = username, placeholder = "Username", icon = Icons.Rounded.Person)
                        Spacer(modifier = Modifier.height(4.dp))
                        PasswordTextField(input = password, placeholder = "Password")
                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = acceptedTerms.value,
                                onCheckedChange = { acceptedTerms.value = it }
                            )
                            Text(text = "I’ve read and accept the Terms and Conditions and Privacy policy",)
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        CustomButton(
                            text = "Sign up",
                            onclick= {
                                authRepository.signUp(
                                    request = SignUpRequest(
                                        businessName = businessName.value,
                                        tin = TIN.value,
                                        firstName = firstName.value,
                                        lastName = lastName.value,
                                        username = username.value,
                                        password = password.value,
                                    )
                                ) { result ->
                                    result.onSuccess { userResponse ->
                                        navController.navigate(Routes.Dashboard.route.replace("{username}", userResponse.username))
                                    }
                                    result.onFailure {
                                        error("Error: ${it.message}")
                                    }
                                }
                            }
                        )
                    }


                }


            }

        }

    }
}
