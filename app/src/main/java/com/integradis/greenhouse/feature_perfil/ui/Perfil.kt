package com.integradis.greenhouse.feature_perfil.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.integradis.greenhouse.shared.ui.InfoField
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(
    navController: NavHostController,
    name: String,
    username: String,
    company: String,
    role: String
){
    val rem = remember {
        mutableStateOf("")
    }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
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
        Box(){
            ElevatedCard(
                modifier = Modifier
                    .padding(15.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                ),
                shape = RoundedCornerShape(1400.dp)
            ){
                GlideImage(
                    imageModel = {"https://www.investmentmonitor.ai/wp-content/uploads/sites/7/2020/07/cropped-Headshot-IM-177x177.png"},
                    modifier = Modifier.size(150.dp),
                    requestOptions = {
                        RequestOptions()
                            .transform(RoundedCorners(1000))
                    },
                )
            }
            Row(
                Modifier.align(Alignment.BottomCenter)
            ) {
                Spacer(Modifier.fillMaxWidth(0.2f))
                IconButton(
                    onClick = { },
                    Modifier.background(PrimaryGreen40, shape = RoundedCornerShape(1000.dp))
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }
            }
        }
        Column {
            InfoField(rem, name, username, company, role)
            Text(text = "Settings", style = Typography.labelLarge, modifier = Modifier.padding(15.dp))
            Text(text = "Forgot your passowrd?",
                style = Typography.labelLarge,
                color = PrimaryGreen40,
                modifier = Modifier
                    .padding(15.dp, 5.dp, 0.dp, 0.dp)
                    .clickable { navController.navigate("ForgotPassword") }
            )
            Text(text = "Delete Account",
                style = Typography.labelLarge,
                color = Color.Red,
                modifier = Modifier
                    .padding(15.dp, 5.dp, 0.dp, 0.dp)
                    .clickable { navController.navigate("ForgotPassword") }
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(15.dp)
                    .width(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .border(1.dp, PrimaryGreen40, RoundedCornerShape(100.dp)),
                colors = ButtonDefaults.buttonColors(
                    Color.White,
                    PrimaryGreen40
                ),
            ) {
                Text(text = "Log Out", style = Typography.labelLarge)
                Icon(
                    imageVector = Icons.Rounded.Logout,
                    contentDescription = "Log Out",
                )
            }
        }
    }
}