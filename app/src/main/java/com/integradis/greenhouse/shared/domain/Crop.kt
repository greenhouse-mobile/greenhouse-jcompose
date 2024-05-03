package com.integradis.greenhouse.shared.domain

typealias Crops = List<Crop>
data class Crop(
    val id: String,
    val startDate: String,
    val phase: CropPhase,
    val state: String
)
