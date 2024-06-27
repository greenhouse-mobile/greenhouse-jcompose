package com.integradis.greenhouse.model.data.crop_records

import com.google.gson.annotations.SerializedName

data class CropRecordsWrapper(
    @SerializedName("records")
    val cropRecords : List<CropRecordData>
)

data class CropRecordData(
    val id: String,

    val author : String,

    val createdDate : String,

    val updatedDate : String,

    val phase : String,

    @SerializedName("payload")
    val phaseData: Payload
)

data class Payload(
    val data: List<Map<String, String>>
)