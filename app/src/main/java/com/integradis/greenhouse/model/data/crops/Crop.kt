package com.integradis.greenhouse.model.data.crops

import com.google.gson.annotations.SerializedName

data class CropWrapper(
    val crops : List<Crop>
)

data class Crop(
    var id: String,
    var name: String,
    var author: String,
    var state: String,
    var phase: String,
    var startDate: String,
)
