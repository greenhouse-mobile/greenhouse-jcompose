package com.integradis.greenhouse.shared.data.remote

class UserServiceFactory {

        companion object {
            fun getUserService(): UserService {
                return ApiClient.getRetrofit().create(UserService::class.java)
            }
        }
}