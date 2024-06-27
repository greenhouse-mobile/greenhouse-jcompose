package com.integradis.greenhouse.model.remote.crops

import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.model.data.crops.CropWrapper
import com.integradis.greenhouse.model.data.crops.NewCrop
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CropService {

    @GET("crops")
    fun getCrops(@Header("Authorization") token: String): Call<CropWrapper>

    @GET("crops/{id}")
    fun getCropById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<Crop>

    @POST("crops")
    fun createCrop(
        @Header("Authorization") token: String,
        @Body crop: NewCrop
    ): Call<NewCrop>
}
