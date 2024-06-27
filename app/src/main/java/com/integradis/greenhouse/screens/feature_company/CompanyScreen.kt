package com.integradis.greenhouse.screens.feature_company

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import coil.compose.rememberImagePainter
import com.integradis.greenhouse.R
import com.integradis.greenhouse.factories.CompanyRepositoryFactory
import com.integradis.greenhouse.factories.CropRepositoryFactory
import com.integradis.greenhouse.factories.ProfileRepositoryFactory
import com.integradis.greenhouse.factories.UserRepositoryFactory
import com.integradis.greenhouse.model.data.profile.Profile
import com.integradis.greenhouse.model.data.user_information.UserInformation
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import com.integradis.greenhouse.shared.ui.InfoField
import com.integradis.greenhouse.shared.ui.SearchCropTextField
import com.integradis.greenhouse.ui.theme.GrayBg
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyScreen(
    navController: NavHostController,
    sharedPreferencesHelper: SharedPreferencesHelper,
) {
    val id = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val tin = remember { mutableStateOf("") }
    val profilePicture = remember { mutableStateOf("") }
    val profileId = remember { mutableStateOf("") }
    val searchEmployee = remember { mutableStateOf("") }
    val employees = remember { mutableStateOf(emptyList<Profile>()) }

    val userRepository = UserRepositoryFactory.getUserRepository(sharedPreferencesHelper)
    userRepository.getMe { user ->
        user?.let {
            profileId.value = it.id
        } ?: run {
            Log.d("dashboard", "Usuario no encontrado")
        }
    }

    val companyRepository = CompanyRepositoryFactory.getCompanyRepository(sharedPreferencesHelper)
    companyRepository.getCompany(profileId.value) { companyData ->
        companyData?.let {
            id.value = it.id
            name.value = it.name
            tin.value = it.tin
            profilePicture.value = it.logoUrl
        } ?: run {
            Log.d("dashboard", "Company not found")
        }
    }

    val profileRepository = ProfileRepositoryFactory.getProfileRepository(sharedPreferencesHelper)
    profileRepository.getProfilesByCompanyId(companyId = id.value) {
        employees.value = it
        Log.d("CropsInProgressScreen", "Employees: $employees")
    }

    val fields = listOf(
        UserInformation(
            title = "Company Name",
            placeholder = name.value,
        ),
        UserInformation(
            title = "TIN",
            placeholder = tin.value,
        ),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
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
                painter = rememberImagePainter(profilePicture.value),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(1400.dp))
            )
        }
        InfoField(fields, navController)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
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

            val scrollState = rememberScrollState()
            val filteredEmployees = if (searchEmployee.value.isNotEmpty()) {
                employees.value.filter {
                    (it.firstName.lowercase() + " " + it.lastName.lowercase()).contains(searchEmployee.value.lowercase())
                }
            } else {
                employees.value
            }
            LazyColumn {
                items(filteredEmployees) { employee ->
                    CoworkerCard(
                        name = "${employee.firstName} ${employee.lastName}",
                        role = employee.role,
                        imageUrl = employee.iconUrl
                    )
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
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(1000.dp))
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
