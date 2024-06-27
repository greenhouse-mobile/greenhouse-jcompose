package com.integradis.greenhouse.model.data.crops

data class UserWrapper(
    val users : List<UserData>
)
data class UserData(
    var id: String,
    var userId: String,
    var firstName: String,
    var lastName: String,
    var iconUrl: String,
    var role: String,
    var username: String
)