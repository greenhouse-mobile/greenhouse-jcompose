package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.request.RequestOptions
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import java.security.MessageDigest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardCard(
    imageUrl: String,
    title: String,
    navController: NavHostController,
    route: String
){
    val rem = remember {
        mutableStateOf("")
    }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
        ElevatedCard(
            modifier = Modifier
                .padding(15.dp)
                .clickable {navController.navigate(route) },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
        ) {
            GlideImage(
                imageModel = { imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Inside,

                    ),
                requestOptions = {
                    RequestOptions()
                        .transform(CutOffCardImage())
                }
            )
            Text(
                text= title,
                style = Typography.labelLarge,
                color = PrimaryGreen40,
                modifier = Modifier.padding(15.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}