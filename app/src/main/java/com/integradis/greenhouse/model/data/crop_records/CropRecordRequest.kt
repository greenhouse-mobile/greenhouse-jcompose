package com.integradis.greenhouse.model.data.crop_records

data class CropRecordRequest(
    val author: String,
    val phase: String,
    val payload: Payload,
    val cropId: String
)

