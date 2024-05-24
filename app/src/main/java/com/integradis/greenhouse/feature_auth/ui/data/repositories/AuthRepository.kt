package com.integradis.greenhouse.feature_auth.ui.data.repositories

import com.integradis.greenhouse.feature_auth.ui.data.remote.AuthService
import com.integradis.greenhouse.feature_auth.ui.data.remote.AuthServiceFactory
import com.integradis.greenhouse.feature_auth.ui.data.remote.SignUpRequest
import com.integradis.greenhouse.feature_auth.ui.data.remote.UserRequest
import com.integradis.greenhouse.feature_auth.ui.data.remote.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val authService: AuthService = AuthServiceFactory.getAuthService()) {

    fun signIn(username: String, password: String, callback: (Result<UserResponse>) -> Unit) {
        val request = UserRequest(username, password)
        val call = authService.signIn(request)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(Result.success(it))
                    } ?: callback(Result.failure(Exception("Response body is null")))
                } else {
                    callback(Result.failure(Exception("Failed to sign in: ${response.message()}")))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }

    fun signUp(
        request: SignUpRequest,
        callback: (Result<UserResponse>) -> Unit
    ){
        val call = authService.signUp(request)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(Result.success(it))
                    } ?: callback(Result.failure(Exception("Response body is null")))
                } else {
                    callback(Result.failure(Exception("Failed to sign up: ${response.message()}")))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }


}