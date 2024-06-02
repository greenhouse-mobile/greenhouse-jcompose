package com.integradis.greenhouse.model.remote.crops

import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.model.data.crops.CropWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CropService {

    @GET("crops")
    fun getCrops(
        @Query("active") endpoint: String
    ) : Call<CropWrapper>

    @GET("crops/{id}")
    fun getCropById(
        @Path("id") id: String): Call<Crop>
}