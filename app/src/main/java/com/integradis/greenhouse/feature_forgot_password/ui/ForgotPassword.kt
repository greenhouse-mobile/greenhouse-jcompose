package com.integradis.greenhouse.feature_forgot_password.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.integradis.greenhouse.shared.domain.UserInformation
import com.integradis.greenhouse.shared.ui.InfoField
import com.integradis.greenhouse.ui.theme.Brown
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(
    navController: NavHostController,
    newPassword: String,
    confirmPassword: String
){
    val remNewPassword = remember {
        mutableStateOf("")
    }
    val remConfirmPassword = remember {
        mutableStateOf("")
    }
    val fields = listOf(
        UserInformation(
            title = "New Password",
            placeholder = newPassword,
        ),
        UserInformation(
            title = "Confirm Password",
            placeholder = confirmPassword,
        )
    )
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = { navController.navigateUp()},
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Go Back",
                    tint = PrimaryGreen40,
                )
            }
            Text(
                text = "Go Back",
                style = Typography.labelLarge,
                color = PrimaryGreen40,
            )
        }
        Text(
            text = "RESET YOUR PASSWORD",
            style = Typography.headlineMedium,
            color = PrimaryGreen40,
            modifier = Modifier.padding(15.dp),
        )
        Icon(
            imageVector = Icons.Rounded.Lock,
            contentDescription = "lock",
            tint = Color.White,
            modifier = Modifier.size(150.dp).background(Brown, shape = RoundedCornerShape(1000.dp)).border(20.dp, Brown, shape = RoundedCornerShape(1000.dp)).padding(25.dp),
            )
        InfoField(fields, navController)

        //if (remNewPassword == remConfirmPassword) {asd = true}

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(15.dp)
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
                .border(0.dp, Color.White, RoundedCornerShape(100.dp)),
            colors = ButtonDefaults.buttonColors(
                PrimaryGreen40,
                Color.White
            ),
            enabled = true,
        ) {
            Text(text = "Change Password", style = Typography.labelLarge)
            Icon(
                imageVector = Icons.Rounded.Lock,
                contentDescription = "Change Password"
            )
        }
    }
}