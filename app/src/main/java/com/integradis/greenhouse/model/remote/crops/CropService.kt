package com.integradis.greenhouse.model.remote.crops

import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.model.data.crops.CropWrapper
import com.integradis.greenhouse.model.data.crops.NewCrop
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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
    @PUT("crops/{id}")
    fun patchCrop(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Field("phase") phase: String,
        @Field("state") state: Boolean
    ): Call<Crop>
}
