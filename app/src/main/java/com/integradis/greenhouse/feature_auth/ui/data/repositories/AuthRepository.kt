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
        val call = authService.signIn(username, password)
        call.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null && users.isNotEmpty()) {
                        val user = users.firstOrNull { it.username == username && it.password == password }
                        if (user != null) {
                            callback(Result.success(user))
                        } else {
                            callback(Result.failure(Exception("Invalid credentials")))
                        }
                    } else {
                        callback(Result.failure(Exception("No users found")))
                    }
                } else {
                    callback(Result.failure(Exception("Failed to sign in: ${response.message()}")))
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
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