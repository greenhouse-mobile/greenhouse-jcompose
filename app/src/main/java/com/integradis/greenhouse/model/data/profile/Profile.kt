package com.integradis.greenhouse.model.data.profile

data class ProfileWrapper(
    val profiles : List<Profile>
)
data class Profile(
    var id: String,
    var userId: String,
    var firstName: String,
    var lastName: String,
    var iconUrl: String,
    var role: String,
)