package com.integradis.greenhouse.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recent_records")
data class RecentRecord(
    @PrimaryKey
    val id: String,

    @SerializedName("phase")
    val phase : String,

    @SerializedName("crop_id")
    val cropId : String,

) {
    constructor() : this("", "", "")

}

