package com.integradis.greenhouse.model.data.authentication_requests

data class UserResponseWrapper(
    val users: List<UserResponse>
)
data class UserResponse(
    val id: String,
    val username: String,
    val password: String,
    val role: String
)