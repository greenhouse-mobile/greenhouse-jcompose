package com.integradis.greenhouse.screens.feature_crop_records.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.integradis.greenhouse.shared.ui.SearchCropTextField
import com.integradis.greenhouse.ui.theme.Typography

@Composable
fun CropRecordInputField(
    title: String,
    content: String,
    onValueChange: (String) -> Unit,
    isEmpty: Boolean,
    contentPlaceholder: String? = ""
    ){
    Text("IM BEING RENDERED")

    Column(
        modifier = Modifier.padding(15.dp),
    ) {
        Text(text = title, style = Typography.labelLarge)
        if (isEmpty){
            TextField(
                value = content,
                onValueChange = onValueChange,
                placeholder = {Text("Ex: $contentPlaceholder")}
            )
        } else {
            TextField(
                value = content,
                onValueChange = onValueChange,
                placeholder = {Text(content)}
            )
        }
    }
}
