package com.integradis.greenhouse.model.data.crop_records

import com.google.gson.annotations.SerializedName

data class CropRecordsWrapper(
    @SerializedName("records")
    val cropRecords : List<CropRecordData>
)

data class CropRecordData(
    val id: String,

    @SerializedName("created_by")
    val author : String,

    @SerializedName("crop_day")
    val cropDay : String,

    @SerializedName("created_at")
    val entryDate : String,

    @SerializedName("updated_at")
    val updateDate : String,

    @SerializedName("payload")
    val phaseData: Payload
)

data class Payload(
    val data: List<Map<String, String>>
)