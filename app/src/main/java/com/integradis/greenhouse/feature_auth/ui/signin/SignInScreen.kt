package com.integradis.greenhouse.feature_auth.ui.signin

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
import com.integradis.greenhouse.R
import com.integradis.greenhouse.shared.ui.CustomButton
import com.integradis.greenhouse.shared.ui.CustomTextField
import com.integradis.greenhouse.shared.ui.PasswordTextField
import com.integradis.greenhouse.shared.ui.SideCustomButton
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun SignInScreen(navigateToSignUp: () -> Unit,
                 navigateToSignIn: () -> Unit,
                 navigateToCrops: () -> Unit) {
    Scaffold { paddingValues ->

        val username = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }

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
                            SideCustomButton(text = "Sign in", navigateToSignIn) //lleva a Sign In
                            Spacer(modifier = Modifier.weight(0.1f))
                            SideCustomButton(text = "Sign up", navigateToSignUp) //lleva a Sign up
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

                        CustomButton(
                            text = "Sign up",
                            navigateToCrops
                        )

                    }
                }
            }
        }


    }
}