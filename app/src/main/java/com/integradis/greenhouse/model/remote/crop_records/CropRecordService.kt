package com.integradis.greenhouse.model.remote.crop_records

import com.integradis.greenhouse.model.data.crop_records.CropRecordData
import com.integradis.greenhouse.model.data.crop_records.CropRecordsWrapper
import com.integradis.greenhouse.model.data.crop_records.NewRecordData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CropRecordService {

    @GET("records/{cropId}/{phase}")
    fun getCropRecords(
        @Header("Authorization") token: String,
        @Path("cropId") cropId: String,
        @Path("phase") phase: String
    ) : Call<CropRecordsWrapper>

    @GET("records/{id}")
    fun getRecordById(
        @Header("Authorization") token: String,
        @Path("id") id: String): Call<CropRecordData>

    @POST("records")
    fun createRecord(
        @Header("Authorization") token: String,
        @Body record: NewRecordData
    ) : Call<NewRecordData>
}