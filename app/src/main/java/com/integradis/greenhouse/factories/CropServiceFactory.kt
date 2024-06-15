package com.integradis.greenhouse.factories

import com.integradis.greenhouse.model.remote.crops.CropService
import com.integradis.greenhouse.network.ApiClient

object CropServiceFactory {

    fun getCropService(): CropService {
        return ApiClient.getRetrofit().create(CropService::class.java)
    }
}