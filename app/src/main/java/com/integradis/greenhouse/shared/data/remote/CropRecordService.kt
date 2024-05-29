package com.integradis.greenhouse.shared.data.remote

import com.integradis.greenhouse.shared.domain.CropRecordsWrapper
import retrofit2.Call
import retrofit2.http.GET
<<<<<<< HEAD
import retrofit2.http.Path
=======
>>>>>>> develop
import retrofit2.http.Query

interface CropRecordService {

<<<<<<< HEAD
    @GET("{cropId}")
    fun getCropRecords(
        @Path("cropId") endpoint1: String,
=======
    @GET("records")
    fun getCropRecords(
        @Query("cropId") endpoint1: String,
>>>>>>> develop
        @Query("phase") endpoint2: String
    ) : Call<CropRecordsWrapper>
}