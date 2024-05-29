package com.integradis.greenhouse.feature_auth.ui.data.remote

import com.integradis.greenhouse.shared.data.remote.ApiClient

class AuthServiceFactory {
    companion object {
        fun getAuthService(): AuthService {
            return ApiClient.getRetrofit().create(AuthService::class.java)
        }
    }
}