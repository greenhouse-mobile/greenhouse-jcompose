package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.model.remote.crop_records.CropRecordService
import com.integradis.greenhouse.factories.CropRecordServiceFactory
import com.integradis.greenhouse.model.data.crop_records.CropRecordRequest
import com.integradis.greenhouse.model.data.crop_records.CropRecordResponse
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

    fun getCropRecords(callback: (List<CropRecordResponse>) -> Unit) {
        token?.let {
            val getCropRecords = cropRecordService.getCropRecords("Bearer $it")

            getCropRecords.enqueue(object: Callback<CropRecordsWrapper> {
                override fun onResponse(call: Call<CropRecordsWrapper>, response: Response<CropRecordsWrapper>) {

                    Log.d("CropRecordRepository", "Respuesta recibida: $response")
                    if (response.isSuccessful) {
                        response.body()?.let { cropRecordsWrapper ->
                            callback(cropRecordsWrapper.records)
                        } ?: run {
                            Log.d("CropRecordRepository", "No records found")
                            callback(emptyList())
                        }
                    } else {
                        Log.d("CropRecordRepository", "Response not successful: ${response.message()}")
                        callback(emptyList())
                    }
                }

                override fun onFailure(call: Call<CropRecordsWrapper>, t: Throwable) {
                    t.message?.let { message ->
                        Log.d("CropRecordRepository", message)
                    }
                    callback(emptyList())
                }
            })
        } ?: run {
            callback(emptyList())
        }
    }

    fun getRecordById(id: String, callback: (List<CropRecordResponse>) -> Unit) {
        token?.let {
            val getRecordById = cropRecordService.getRecordById("Bearer $it", id)

            getRecordById.enqueue(object : Callback<CropRecordsWrapper> {
                override fun onResponse(
                    call: Call<CropRecordsWrapper>,
                    response: Response<CropRecordsWrapper>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { cropRecordsWrapper ->
                            callback(cropRecordsWrapper.records) //guarda la nueva lista de registros
                        } ?: run {
                            Log.d("RecordRepository", "No records found")
                            callback(emptyList())
                        }
                    } else {
                        Log.d("RecordRepository", "Response not successful: ${response.message()}")
                        callback(emptyList())
                    }
                }

                override fun onFailure(call: Call<CropRecordsWrapper>, t: Throwable) {
                    t.message?.let { message ->
                        Log.d("RecordRepository", message)
                    }
                    callback(emptyList())
                }
            })
        }
    }

    fun postCropRecord(cropRecordRequest: CropRecordRequest, callback: (Result<CropRecordResponse>) -> Unit) {
        token?.let {
            val postCropRecordCall = cropRecordService.postCropRecord(cropRecordRequest)

            postCropRecordCall.enqueue(object: Callback<CropRecordsWrapper> {
                override fun onResponse(call: Call<CropRecordsWrapper>, response: Response<CropRecordsWrapper>) {
                    if (response.isSuccessful) {
                        response.body()?.let { cropRecordsWrapper ->
                            callback(Result.success(cropRecordsWrapper.records.first()))
                        } ?: run {
                            Log.d("CropRecordRepository", "No records found")
                            callback(Result.failure(Exception("No records found")))
                        }
                    } else {
                        Log.d("CropRecordRepository", "Response not successful: ${response.message()}")
                        callback(Result.failure(Exception("Response not successful: ${response.message()}")))
                    }
                }

                override fun onFailure(call: Call<CropRecordsWrapper>, t: Throwable) {
                    t.message?.let { message ->
                        Log.d("CropRecordRepository", message)
                    }
                    callback(Result.failure(t))
                }
            })
        } ?: run {
            callback(Result.failure(Exception("Token is null")))
        }
    }

}