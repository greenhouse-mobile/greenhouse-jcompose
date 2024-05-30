package com.integradis.greenhouse.shared.data.remote

object CropRecordServiceFactory {

    fun getCropRecordService(): CropRecordService {
        return ApiClient.getRetrofit().create(CropRecordService::class.java)
    }
}