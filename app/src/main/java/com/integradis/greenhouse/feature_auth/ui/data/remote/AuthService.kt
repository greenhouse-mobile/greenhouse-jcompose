package com.integradis.greenhouse.feature_auth.ui.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("users") //api/v1/authentication/sign-in
    fun signIn(@Body request: UserRequest): Call<UserResponse>

    @POST("users") //api/v1/authentication/sign-up
    fun signUp(@Body request: SignUpRequest): Call<UserResponse>
}