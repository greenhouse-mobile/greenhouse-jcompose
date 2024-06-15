package com.integradis.greenhouse.model.remote.crop_records

import com.integradis.greenhouse.model.data.crop_records.CropRecordsWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CropRecordService {

    @GET("records")
    fun getCropRecords(
        @Query("cropId") endpoint1: String,
        @Query("phase") endpoint2: String
    ) : Call<CropRecordsWrapper>
}