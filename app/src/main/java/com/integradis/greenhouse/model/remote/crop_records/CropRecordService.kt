package com.integradis.greenhouse.model.remote.crop_records

import com.integradis.greenhouse.model.data.crop_records.CropRecordRequest
import com.integradis.greenhouse.model.data.crop_records.CropRecordsWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Path

interface CropRecordService {

    @GET("records")
    fun getCropRecords(
        @Header("Authorization") token: String,
    ) : Call<CropRecordsWrapper>

    @GET("records/{id}")
    fun getRecordById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<CropRecordsWrapper>

    @POST("records")
    fun postCropRecord(@Body recordsRequest: CropRecordRequest): Call<CropRecordsWrapper>

}