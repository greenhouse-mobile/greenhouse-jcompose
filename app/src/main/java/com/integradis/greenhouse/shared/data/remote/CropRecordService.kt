package com.integradis.greenhouse.shared.data.remote

import com.integradis.greenhouse.shared.domain.CropRecordsWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CropRecordService {

    @GET("{cropId}")
    fun getCropRecords(
        @Path("cropId") endpoint1: String,
        @Query("phase") endpoint2: String
    ) : Call<CropRecordsWrapper>
}