package com.integradis.greenhouse.shared.domain

import com.google.gson.annotations.SerializedName

data class CropWrapper(
    val crops : List<Crop>
)

data class Crop(
    var id: String,

    @SerializedName("createdDate")
    var startDate: String,
    var name: String,
    var author: String,
    var phase: CropPhase,

    @SerializedName("active")
    var state: String
)
