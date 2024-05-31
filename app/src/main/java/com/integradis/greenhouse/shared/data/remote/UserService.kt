package com.integradis.greenhouse.shared.data.remote

import com.integradis.greenhouse.shared.domain.UserWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("profiles")
    fun getUser(
        @Query("userId") username: String
    ) : Call<UserWrapper>
}