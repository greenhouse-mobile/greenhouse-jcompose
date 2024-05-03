package com.integradis.greenhouse.shared.domain

typealias Crops = List<Crop>
data class Crop(
    var id: String,
    var startDate: String,
    var phase: CropPhase,
    var state: String
)
