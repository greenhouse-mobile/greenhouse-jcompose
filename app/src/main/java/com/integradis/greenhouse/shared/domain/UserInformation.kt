package com.integradis.greenhouse.shared.domain

import androidx.compose.runtime.MutableState

data class UserInformation(
    val title: String,
    val placeholder: String,
    val input: MutableState<String>,
) {}