package com.integradis.greenhouse.feature_auth.ui.data.remote

data class SignUpRequest(
    val businessName: String,
    val tin: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
)
