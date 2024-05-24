package com.integradis.greenhouse.feature_auth.ui.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//usar comando json-server --watch db.json --host 0.0.0.0 --port 3001 , para encender el json server

interface AuthService {
    @GET("users") //api/v1/authentication/sign-in
    fun signIn(@Query("username") username: String, @Query("password") password: String): Call<List<UserResponse>>

    @POST("users") //api/v1/authentication/sign-up
    fun signUp(@Body request: SignUpRequest): Call<UserResponse>
}
