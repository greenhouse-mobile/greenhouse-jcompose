package com.integradis.greenhouse.model.remote.profiles
import com.integradis.greenhouse.model.data.profile.ProfileWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProfileService {

    @GET("profiles/companies/{companyId}")
    fun getProfilesByCompanyId(
        @Header("Authorization") token: String,
        @Path("companyId") companyId: String
    ): Call<ProfileWrapper>

}
