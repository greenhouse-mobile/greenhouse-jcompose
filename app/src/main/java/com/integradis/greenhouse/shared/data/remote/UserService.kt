package com.integradis.greenhouse.shared.data.remote


import com.integradis.greenhouse.model.data.crops.UserData
import com.integradis.greenhouse.model.data.crops.UserWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserService {
    @GET("profiles")
    fun getUser(
        @Query("userId") userId: String
    ) : Call<UserWrapper>

    // Get profiles/users/me
    @GET("profiles/users/me")
    fun getMe(@Header("Authorization") token: String) : Call<UserData>
}