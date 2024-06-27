package com.integradis.greenhouse.factories

import com.integradis.greenhouse.model.remote.profiles.ProfileService
import com.integradis.greenhouse.network.ApiClient

object ProfileServiceFactory {
    fun getProfileService(): ProfileService {
        return ApiClient.getRetrofit().create(ProfileService::class.java)
    }
}