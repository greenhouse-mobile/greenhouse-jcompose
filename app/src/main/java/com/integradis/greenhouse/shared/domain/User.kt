package com.integradis.greenhouse.shared.domain

import com.google.gson.annotations.SerializedName

data class UserWrapper(
    @SerializedName("profiles")
    val users : List<UserData>
)
data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("iconUrl")
    val image: String
)