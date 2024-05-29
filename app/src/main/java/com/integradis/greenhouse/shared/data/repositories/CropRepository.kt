package com.integradis.greenhouse.shared.data.repositories

import android.util.Log
import com.integradis.greenhouse.shared.data.remote.CropService
import com.integradis.greenhouse.shared.data.remote.CropServiceFactory
import com.integradis.greenhouse.shared.domain.Crop
import com.integradis.greenhouse.shared.domain.CropWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CropRepository(private val cropService : CropService = CropServiceFactory.getCropService()) {

    fun getCrops(endpoint: String, callback: (List<Crop>) -> Unit) {
        val getCrops = cropService.getCrops(endpoint)

        getCrops.enqueue(object: Callback<CropWrapper> {
            override fun onResponse(
                call: Call<CropWrapper>,
                response: Response<CropWrapper>
            ) {
                if(response.isSuccessful){
                    callback(response.body()?.crops ?: emptyList())
                }
            }

            override fun onFailure(
                call: Call<CropWrapper>,
                t: Throwable
            ) {
                t.message?.let {
                    Log.d("CropRepository", it)
                }
            }
        })
    }
}