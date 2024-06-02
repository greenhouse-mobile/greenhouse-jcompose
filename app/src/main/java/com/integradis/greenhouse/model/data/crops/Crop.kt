package com.integradis.greenhouse.model.data.crops

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
    var phase: String,

    @SerializedName("active")
    var state: String
)
