package com.integradis.greenhouse.factories

import com.integradis.greenhouse.model.remote.authentication.AuthService
import com.integradis.greenhouse.network.ApiClient

class AuthServiceFactory {
    companion object {
        fun getAuthService(): AuthService {
            return ApiClient.getRetrofit().create(AuthService::class.java)
        }
    }
}