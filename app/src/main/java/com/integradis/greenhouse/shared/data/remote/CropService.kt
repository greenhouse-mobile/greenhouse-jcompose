package com.integradis.greenhouse.shared.data.remote

import com.integradis.greenhouse.shared.domain.CropWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CropService {

    @GET("crops")
    fun getCrops(
        @Query("active") endpoint: String
    ) : Call<CropWrapper>
}