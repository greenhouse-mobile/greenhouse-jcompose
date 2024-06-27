package com.integradis.greenhouse.model.data.crops
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

data class NewCrop(
    var name: String,
    var author: String,
)

data class UpdateCrop(
    var phase: String,
    var state: Boolean = true,
)
