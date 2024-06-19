package com.integradis.greenhouse.screens.feature_authentication_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.integradis.greenhouse.R
import com.integradis.greenhouse.screens.feature_main.Routes
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun AuthenticationHomeScreen(navController: NavController) {
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
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Box(
                    modifier = Modifier.padding(paddingValues),
                    contentAlignment = Alignment.BottomCenter
                ){
                    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = 15.dp)
                        .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(bottom = 32.dp)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "WELCOME TO \nGREENHOUSE!",
                            style = Typography.titleLarge,
                            color = Color(0xFF4C6444)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = "To use the app, please log in or sign up",
                            style = Typography.labelLarge,
                            textAlign = TextAlign.Center,
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Button(
                            onClick = { navController.navigate(Routes.SignIn.route) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF67864A),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Log In")
                        }

                        Spacer(modifier = Modifier.height(250.dp))
                    }
                }

            }
        }

    }

}