package com.integradis.greenhouse.screens.feature_dashboard

import android.app.Application
import android.util.Log
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
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.integradis.greenhouse.R
import com.integradis.greenhouse.factories.UserRepositoryFactory
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import com.integradis.greenhouse.data.RecordViewModel
import com.integradis.greenhouse.data.RecordViewModelFactory
import com.integradis.greenhouse.screens.feature_main.Routes
import com.integradis.greenhouse.shared.ui.DashboardCard
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    sharedPreferencesHelper: SharedPreferencesHelper,
    ){
    val userName = remember {
        mutableStateOf("")
    }
    val userRepository = UserRepositoryFactory.getUserRepository(sharedPreferencesHelper)
    userRepository.getMe { user ->
        user?.let {
            userName.value = it.firstName + " " + it.lastName

        } ?: run {
            Log.d("dashboard", "Usuario no encontrado")
        }
    }
    recordViewModel: RecordViewModel = viewModel(factory = RecordViewModelFactory(LocalContext.current.applicationContext as Application))
){
    val record by recordViewModel.record.observeAsState()
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
            text = "Welcome, " + userName.value,
            style = Typography.titleLarge,
            color = Color(0xFF465B3F),
            modifier = Modifier.padding(15.dp)
        )
        record?.let {
            Card (
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate("${Routes.CropRecords.route}/${it.cropId}/${it.phase}") }
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
                    imageModel = { "https://img.freepik.com/free-vector/clock-basic-style_78370-6152.jpg?t=st=1719459798~exp=1719463398~hmac=256c8be5ddd7b96ababf700af4fb0959026a4d68b1b04d0b2e4ca24b7e0752b8&w=826" },
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
            imageUrl = "https://img.freepik.com/free-photo/asian-woman-studying-different-plants-with-tablet_23-2148776800.jpg?t=st=1718303749~exp=1718307349~hmac=4ccf17973d553b198da732c2e6027fc0bf5f735c80a93ce28e95331f26e88926&w=2000",
            title = "Crops In Progress",
            navController,
            route = "CropsInProgress"
        )
        DashboardCard(
            imageUrl = "https://img.freepik.com/free-photo/ring-binder-used-stored-documents_23-2149362548.jpg?t=st=1718303998~exp=1718307598~hmac=9a9eaad26b4de7184802f61144ff7542f827916dfab101bd98db69b127bc60d6&w=2000",
            title = "Crops Archive",
            navController,
            route = "Archives"
        )

    }
}