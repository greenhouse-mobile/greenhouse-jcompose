package com.integradis.greenhouse.model.data.authentication_requests

/*data class UserResponseWrapper(
    val users: List<UserResponse>,
    val token: String
)
data class UserResponse(
    val id: String,
    val username: String,
    val password: String,
    val role: String
)*/

data class UserResponseWrapper(
    val profile: UserResponse,
    val token: String
)

data class UserResponse(
    val id: String,
    val userId: String,
    val firstName: String,
    val lastName: String,
    val iconUrl: String,
    val role: String
)