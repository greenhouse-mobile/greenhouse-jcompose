package com.integradis.greenhouse.model.data.crop_records

import com.google.gson.annotations.SerializedName

data class CropRecordsWrapper(
    val records : List<CropRecordResponse>
)

data class CropRecordResponse(
    val id: String,
    val createdDate : String,
    val updatedDate : String,
    val author : String,
    val phase : String,
    val phaseData: Payload
)

data class Payload(
    val data: List<Map<String, String>>
)