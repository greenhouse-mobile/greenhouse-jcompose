package com.integradis.greenhouse.model.remote.company

import com.integradis.greenhouse.model.data.company.CompanyData
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

interface CompanyService {
    @GET("companies")
    fun getCompany(
        @Header("Authorization") token: String,
        @Query("profileId") endpoint1: String,
    ): Call<CompanyData>
}