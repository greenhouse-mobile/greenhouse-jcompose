package com.integradis.greenhouse.feature_perfil.ui

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.integradis.greenhouse.R
import com.integradis.greenhouse.shared.data.repositories.UserRepository
import com.integradis.greenhouse.shared.domain.UserData
import com.integradis.greenhouse.shared.ui.InfoField
import com.integradis.greenhouse.shared.domain.UserInformation
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography
import coil.compose.rememberImagePainter

@Composable
fun Perfil(
    navController: NavHostController,
    company: String,
    userRepository: UserRepository = UserRepository()
) {
    val userData = remember {
        mutableStateOf(emptyList<UserData>())
    }
    userRepository.getUser("wsmith") {
        userData.value = it
        Log.d("Perfil", userData.value.toString())
    }
    var userName by remember { mutableStateOf("") }
    var userUsername by remember { mutableStateOf("") }
    var userRole by remember { mutableStateOf("") }
    var userImage by remember { mutableStateOf("") }

    for (i in userData.value) {
        if (i.id == "99c5c19-c426-453a-a93b-99a02fa136d2") {
            userName = i.firstName + " " + i.lastName
            userUsername = i.userId
            userRole = i.role
            userImage = i.image
        }
    }

    val fields = listOf(
        UserInformation(
            title = "Name",
            placeholder = userName,
        ),
        UserInformation(
            title = "Username",
            placeholder = userUsername,
        ),
        UserInformation(
            title = "Company",
            placeholder = company,
        ),
        UserInformation(
            title = "Role within the company",
            placeholder = userRole,
        ),
    )

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        // Aquí puedes manejar la subida de la imagen al servidor si es necesario
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
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
        Box {
            ElevatedCard(
                modifier = Modifier
                    .padding(15.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                ),
                shape = RoundedCornerShape(1400.dp)
            ) {
                val painter = if (imageUri != null) {
                    rememberImagePainter(data = imageUri)
                } else {
                    painterResource(id = R.drawable.max2)
                }
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(1400.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                Modifier.align(Alignment.BottomCenter)
            ) {
                Spacer(Modifier.fillMaxWidth(0.2f))
                IconButton(
                    onClick = { launcher.launch("image/*") },
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
            InfoField(fields, navController)
            Text(
                text = "Settings",
                style = Typography.labelLarge,
                modifier = Modifier.padding(15.dp)
            )
            Text(
                text = "Forgot your password?",
                style = Typography.labelLarge,
                color = PrimaryGreen40,
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
