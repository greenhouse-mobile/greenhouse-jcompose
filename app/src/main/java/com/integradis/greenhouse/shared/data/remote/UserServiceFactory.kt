package com.integradis.greenhouse.shared.data.remote

import com.integradis.greenhouse.network.ApiClient

object UserServiceFactory {

    fun getUserService(): UserService {
        return ApiClient.getRetrofit().create(UserService::class.java)
    }
}