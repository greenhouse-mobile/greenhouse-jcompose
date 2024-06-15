package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.model.remote.crop_records.CropRecordService
import com.integradis.greenhouse.factories.CropRecordServiceFactory
import com.integradis.greenhouse.model.data.crop_records.CropRecordData
import com.integradis.greenhouse.model.data.crop_records.CropRecordsWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CropRecordRepository(private val cropRecordService : CropRecordService = CropRecordServiceFactory.getCropRecordService()) {

    fun getCropRecords(endpoint: String, endpoint2 : String, callback: (List<CropRecordData>) -> Unit){
        val getCropRecords = cropRecordService.getCropRecords(endpoint, endpoint2)

        getCropRecords.enqueue(object: Callback<CropRecordsWrapper>{
            override fun onResponse(
                call: Call<CropRecordsWrapper>,
                response: Response<CropRecordsWrapper>
            ) {
                if (response.isSuccessful){
                    callback(response.body()?.cropRecords ?: emptyList())
                }
            }

            override fun onFailure(
                call: Call<CropRecordsWrapper>,
                t: Throwable
            ) {
                t.message?.let {
                    Log.d("CropRecordRepository", it)
                }
            }

        })
    }
}