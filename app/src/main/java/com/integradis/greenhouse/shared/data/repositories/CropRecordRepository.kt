package com.integradis.greenhouse.shared.data.repositories

import android.util.Log
import com.integradis.greenhouse.shared.data.remote.CropRecordService
import com.integradis.greenhouse.shared.data.remote.CropRecordServiceFactory
import com.integradis.greenhouse.shared.domain.CropRecordData
import com.integradis.greenhouse.shared.domain.CropRecordsWrapper
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