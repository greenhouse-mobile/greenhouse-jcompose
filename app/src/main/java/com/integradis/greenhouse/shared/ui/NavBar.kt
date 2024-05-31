package com.integradis.greenhouse.shared.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    navController: NavHostController
){
    val rem = remember {
        mutableStateOf("")
    }
    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier.background(PrimaryGreen40, shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)).padding(15.dp),
    )
    {
        IconButton(
            onClick = { navController.navigate("Perfil")},
            modifier = Modifier.weight(1F)
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "AccountCircle",
                tint = Color.White
            )
        }
        IconButton(
            onClick = {navController.navigate("Dashboard") },
            modifier = Modifier.weight(1F)
        ) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "Home",
                tint = Color.White
            )
        }
        IconButton(
            onClick = { navController.navigate("Company")},
            modifier = Modifier.weight(1F)
        ) {
            Icon(
                imageVector = Icons.Rounded.Apartment,
                contentDescription = "Mail",
                tint = Color.White
            )
        }
    }
}