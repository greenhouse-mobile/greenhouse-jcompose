package com.integradis.greenhouse.shared.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.integradis.greenhouse.shared.domain.Information
import com.integradis.greenhouse.ui.theme.GrayTextField40
import com.integradis.greenhouse.ui.theme.PrimaryGreen40
import com.integradis.greenhouse.ui.theme.Typography


@Composable
fun InfoField(
    fields: List<Information>,
    state: Boolean,
) {
    val rem = remember {
        mutableStateOf("")

    }

    Column (
        modifier = Modifier.padding(15.dp),
    )
        {
        fields.forEach {fields->
            Text(text = fields.title, style = Typography.labelLarge, modifier = Modifier.padding(15.dp))
            TextField(
                placeholder = {
                    Text(text = fields.placeholder, style = Typography.labelLarge)
                },
                enabled = state,
                value = fields.input.value,
                onValueChange = {
                    fields.input.value = it
                },
                maxLines = 1,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GrayTextField40,
                    unfocusedContainerColor = GrayTextField40,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = PrimaryGreen40,
                    selectionColors = TextSelectionColors(
                        handleColor = PrimaryGreen40,
                        backgroundColor = PrimaryGreen40
                    )
                ),
                textStyle = Typography.labelMedium,
            )
        }
    }
}

