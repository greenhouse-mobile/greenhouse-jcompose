package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.model.remote.crops.CropService
import com.integradis.greenhouse.factories.CropServiceFactory
import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.model.data.crops.CropWrapper
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CropRepository(
    private val cropService: CropService = CropServiceFactory.getCropService(),
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    private val token: String?
        get() = sharedPreferencesHelper.getToken()

    fun getCrops(callback: (List<Crop>) -> Unit) {
        token?.let {
            val getCrops = cropService.getCrops("Bearer $it")

            getCrops.enqueue(object : Callback<CropWrapper> {
                override fun onResponse(
                    call: Call<CropWrapper>,
                    response: Response<CropWrapper>
                ) {
                    if (response.isSuccessful) {
                        callback(response.body()?.crops ?: emptyList())
                    }
                }

                override fun onFailure(
                    call: Call<CropWrapper>,
                    t: Throwable
                ) {
                    t.message?.let { message ->
                        Log.d("CropRepository", message)
                    }
                }
            })
        }
    }

    fun getCropById(id: String, callback: (Crop) -> Unit) {
        token?.let {
            val getCropById = cropService.getCropById("Bearer $it", id = id)

            getCropById.enqueue(object : Callback<Crop> {
                override fun onResponse(call: Call<Crop>, response: Response<Crop>) {
                    if (response.isSuccessful) {
                        response.body()?.let { crop ->
                            callback(crop)
                        }
                    }
                }

                override fun onFailure(call: Call<Crop>, t: Throwable) {
                    t.message?.let { message ->
                        Log.d("CropRepository", message)
                    }
                }
            })
        }
    }
}
