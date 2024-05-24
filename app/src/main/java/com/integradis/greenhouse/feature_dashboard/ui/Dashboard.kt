package com.integradis.greenhouse.feature_dashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.integradis.greenhouse.R
import com.integradis.greenhouse.shared.ui.DashboardCard
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
    username: String,
    navController: NavHostController
){
    val rem = remember {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo_verde),
                contentDescription = "",
                modifier = Modifier.size(20.dp).clip(RoundedCornerShape(80.dp))
            )
            Text(
                text = "Greenhouse",
                style = Typography.labelLarge,
                color = Color(0xFF465B3F),
            )
            Column (horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()){
                IconButton(
                    onClick = { navController.navigate("Notification")},
                ) {
                    Icon(
                        imageVector = Icons.Rounded.NotificationsNone,
                        contentDescription = "Logo",
                        tint = PrimaryGreen40
                    )
                }
            }
        }
        Text(
            text = "Welcome, " + username,
            style = Typography.titleLarge,
            color = Color(0xFF465B3F),
            modifier = Modifier.padding(15.dp)
        )
        Card (
            modifier = Modifier
                .padding(10.dp)
                .clickable { }
        ){
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "Return to your most recent record",
                    style = Typography.bodyLarge,
                    color = Color(0xFF465B3F),
                    modifier = Modifier.weight(1F)
                )
                GlideImage(
                    imageModel = { "https://icon-library.com/images/no-picture-available-icon/no-picture-available-icon-1.jpg" },
                    modifier = Modifier.size(80.dp),
                    requestOptions = {
                        RequestOptions()
                            .transform(RoundedCorners(900))
                    }
                )
            }
        }
        Text(
            text = "Dashboard",
            style = Typography.titleLarge,
            color = Color(0xFF465B3F),
            modifier = Modifier.padding(15.dp)
        )
        DashboardCard(
            imageUrl = "https://icon-library.com/images/no-picture-available-icon/no-picture-available-icon-1.jpg",
            title = "Crops In Progress",
            navController,
            route = "CropsInProgress"
        )
        DashboardCard(
            imageUrl = "https://icon-library.com/images/no-picture-available-icon/no-picture-available-icon-1.jpg",
            title = "Crops Archive",
            navController,
            route = "Archives"
        )

    }
}
