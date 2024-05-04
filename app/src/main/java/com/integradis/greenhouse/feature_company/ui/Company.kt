package com.integradis.greenhouse.feature_company.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.integradis.greenhouse.R
import com.integradis.greenhouse.shared.domain.Coworkers
import com.integradis.greenhouse.shared.domain.UserInformation
import com.integradis.greenhouse.shared.ui.InfoField
import com.integradis.greenhouse.shared.ui.SearchCropTextField
import com.integradis.greenhouse.ui.theme.GrayBg
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Company(
    navController: NavHostController,
    companyName: String,
    TIN: String,
) {
    val rem = remember {
        mutableStateOf("")
    }
    val searchEmployee = remember {
        mutableStateOf("")
    }
    val fields = listOf(
        UserInformation(
            title = "Company Name",
            placeholder = companyName,
            input = rem
        ),
        UserInformation(
            title = "TIN",
            placeholder = TIN,
            input = rem
        ),
    )
    val coworkers = mutableListOf(
        Coworkers("Alan", "Supervising technician"),
        Coworkers("Eric", "Supervising technician"),
        Coworkers("Nicolas", "Supervising technician"),
        Coworkers("Carlo", "Supervising technician"),
        Coworkers("Lucero", "Supervising technician"),
        Coworkers("Dobby", "Supervising technician"),
        Coworkers("Jessica", "Supervising technician"),
        Coworkers("Casimiro", "Supervising technician"),
        Coworkers("Max", "Supervising technician"),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .height(intrinsicSize = IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
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
        ElevatedCard(
            modifier = Modifier
                .padding(15.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 15.dp
            ),
            shape = RoundedCornerShape(1400.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.campo_alegre),
                contentDescription = "",
                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(1400.dp))
            )
        }
        InfoField(fields, false, navController)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .background(GrayBg, shape = RoundedCornerShape(0.dp))
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Employees",
                style = Typography.labelLarge,
                color = PrimaryGreen40,
            )
            SearchCropTextField(
                input = searchEmployee,
                placeholder = "Search employees...",
            )

            if (searchEmployee.value != "") {
                coworkers.forEach { coworker ->
                    if (coworker.name.lowercase() == searchEmployee.value.lowercase()) {
                        CoworkerCard(coworker.name, coworker.role, "https://i.imgur.com/xPyz8mG.png")

                    }
                }
            } else {
                coworkers.forEach { coworker ->
                    CoworkerCard(coworker.name, coworker.role, "https://i.imgur.com/xPyz8mG.png")
                }
            }
        }
    }
}

@Composable
fun CoworkerCard(
    name: String,
    role: String,
    imageUrl: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(1.dp, PrimaryGreen40, RoundedCornerShape(10.dp))
    ) {
        Row (modifier = Modifier.padding(10.dp))
        {
            Image(
                painter = painterResource(id = R.drawable.max1),
                contentDescription = "",
                modifier = Modifier.size(45.dp).clip(RoundedCornerShape(1000.dp))
            )
            Spacer(Modifier.fillMaxWidth(0.1f))
            Column {
                Text(
                    text = name,
                    style = Typography.labelLarge,
                    color = Color.Black,
                )
                Text(
                    text = role,
                    style = Typography.labelLarge,
                    color = PrimaryGreen40,
                )
            }
        }
    }
}