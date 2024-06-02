package com.integradis.greenhouse.repositories

import com.integradis.greenhouse.factories.AuthServiceFactory
import com.integradis.greenhouse.model.data.authentication_requests.UserResponse
import com.integradis.greenhouse.model.data.authentication_requests.UserResponseWrapper
import com.integradis.greenhouse.model.remote.authentication.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val authService: AuthService = AuthServiceFactory.getAuthService()) {

    fun signIn(username: String, password: String, callback: (Result<UserResponse>) -> Unit) {
        val call = authService.signIn(username, password)
        call.enqueue(object : Callback<UserResponseWrapper> {
            override fun onResponse(call: Call<UserResponseWrapper>, response: Response<UserResponseWrapper>) {
                if (response.isSuccessful) {
                    val users = response.body()?.users
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

            override fun onFailure(call: Call<UserResponseWrapper>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }

}
