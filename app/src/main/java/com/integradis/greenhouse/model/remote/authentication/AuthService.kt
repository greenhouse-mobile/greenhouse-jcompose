package com.integradis.greenhouse.model.remote.authentication

import com.integradis.greenhouse.model.data.authentication_requests.UserResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface AuthService {
    @GET("users") //api/v1/authentication/sign-in
    fun signIn(@Query("username") username: String, @Query("password") password: String): Call<UserResponseWrapper>
}
