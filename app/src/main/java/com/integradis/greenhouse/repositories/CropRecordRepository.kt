package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.model.remote.crop_records.CropRecordService
import com.integradis.greenhouse.factories.CropRecordServiceFactory
import com.integradis.greenhouse.model.data.crop_records.CropRecordData
import com.integradis.greenhouse.model.data.crop_records.CropRecordsWrapper
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CropRecordRepository(
    private val cropRecordService : CropRecordService = CropRecordServiceFactory.getCropRecordService(),
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    private val token: String?
        get() = sharedPreferencesHelper.getToken()

    fun getCropRecords(endpoint: String, endpoint2 : String, callback: (List<CropRecordData>) -> Unit){
        token?.let {
            val getCropRecords = cropRecordService.getCropRecords("Bearer $it", endpoint, endpoint2)

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
                    t.message?.let { message ->
                        Log.d("CropRecordRepository", message)
                    }
                }

            })
        }
    }

    fun getRecordById(id: String, callback: (CropRecordData) -> Unit) {
        token?.let {
            val getRecordById = cropRecordService.getRecordById("Bearer $it", id = id)

            getRecordById.enqueue(object: Callback<CropRecordData>{
                override fun onResponse(call: Call<CropRecordData>, response: Response<CropRecordData>) {
                    if (response.isSuccessful) {
                        Log.d("Record", response.body().toString())
                        callback(response.body() as CropRecordData)
                    }
                }

                override fun onFailure(call: Call<CropRecordData>, t: Throwable) {
                    t.message?.let { message ->
                        Log.d("RecordRepository", message)
                    }
                }
            })
        }
    }
}