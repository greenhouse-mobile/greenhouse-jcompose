package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.integradis.greenhouse.model.data.user_information.UserInformation
import com.integradis.greenhouse.ui.theme.GrayTextField40
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoField(
    fields: List<UserInformation>,
    navController: NavHostController
) {
    val rem = remember {
        mutableStateOf("")

    }

    Column (
        modifier = Modifier.padding(15.dp),
    )
        {
        fields.forEach {fields->
            if (fields.title == "Company"){
                Row {
                    Text(text = fields.title, style = Typography.labelLarge, modifier = Modifier.padding(15.dp).clickable { navController.navigate("Company") })
                    IconButton(
                        onClick = { navController.navigate("Company")},
                        modifier = Modifier.align(Alignment.CenterVertically)
                        ){
                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = "Arrow",
                        )
                    }
                }
            }
            else {
                Text(text = fields.title, style = Typography.labelLarge, modifier = Modifier.padding(15.dp))
            }
            Text(
                text = fields.placeholder, style = Typography.labelLarge, modifier = Modifier.fillMaxWidth().background(GrayTextField40, RoundedCornerShape(10.dp)).padding(15.dp),
            )
        }
    }
}

