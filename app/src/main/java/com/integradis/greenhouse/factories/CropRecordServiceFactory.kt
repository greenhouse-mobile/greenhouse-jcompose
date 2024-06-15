package com.integradis.greenhouse.factories

import com.integradis.greenhouse.model.remote.crop_records.CropRecordService
import com.integradis.greenhouse.network.ApiClient

object CropRecordServiceFactory {

    fun getCropRecordService(): CropRecordService {
        return ApiClient.getRetrofit().create(CropRecordService::class.java)
    }
}