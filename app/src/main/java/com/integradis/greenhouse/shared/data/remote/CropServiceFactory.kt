package com.integradis.greenhouse.shared.data.remote

import retrofit2.create

object CropServiceFactory {

    fun getCropService(): CropService {
        return ApiClient.getRetrofit().create(CropService::class.java)
    }
}