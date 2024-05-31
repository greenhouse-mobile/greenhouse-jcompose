package com.integradis.greenhouse.shared.data.remote

object UserServiceFactory {

    fun getUserService(): UserService {
        return ApiClient.getRetrofit().create(UserService::class.java)
    }
}