package com.integradis.greenhouse.model.remote.authentication

import com.integradis.greenhouse.model.data.authentication_requests.UserRequest
import com.integradis.greenhouse.model.data.authentication_requests.UserResponseWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthService {

    @POST("auth/sign-in") //api/v1/auth/sign-in
    fun signIn(@Body loginRequest: UserRequest): Call<UserResponseWrapper>
}
