package com.integradis.greenhouse.feature_auth.ui.data.remote

data class UserResponse(
    val id: String,
    val username: String,
    val password: String,
    val role: String
)