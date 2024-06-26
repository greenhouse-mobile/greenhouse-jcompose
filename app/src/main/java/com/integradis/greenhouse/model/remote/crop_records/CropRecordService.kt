package com.integradis.greenhouse.model.remote.crop_records

import com.integradis.greenhouse.model.data.crop_records.CropRecordData
import com.integradis.greenhouse.model.data.crop_records.CropRecordsWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CropRecordService {

    @GET("records")
    fun getCropRecords(
        @Header("Authorization") token: String,
        @Query("cropId") endpoint1: String,
        @Query("phase") endpoint2: String
    ) : Call<CropRecordsWrapper>

    @GET("records/{id}")
    fun getRecordById(
        @Header("Authorization") token: String,
        @Path("id") id: String): Call<CropRecordData>
}