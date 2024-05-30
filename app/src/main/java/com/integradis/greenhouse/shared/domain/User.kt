package com.integradis.greenhouse.shared.domain

import com.google.gson.annotations.SerializedName

data class UserWrapper(
    @SerializedName("user")
    val users : List<UserData>
)
data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("role")
    val role: String
)