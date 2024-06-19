package com.integradis.greenhouse.data.remote

import com.integradis.greenhouse.data.model.RecentRecord
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecentRecordService {
    @GET("{id}")
    suspend fun fetchRecentRecordById(@Path("id") id: String): Response<RecentRecord>
}