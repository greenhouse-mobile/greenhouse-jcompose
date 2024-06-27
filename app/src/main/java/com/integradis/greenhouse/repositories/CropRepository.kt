package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.model.remote.crops.CropService
import com.integradis.greenhouse.factories.CropServiceFactory
import com.integradis.greenhouse.model.data.crops.Crop
import com.integradis.greenhouse.model.data.crops.CropWrapper
import com.integradis.greenhouse.model.data.crops.NewCrop
import com.integradis.greenhouse.model.data.crops.UpdateCrop
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

    fun createCrop(newCrop: NewCrop, callback: (Crop) -> Unit) {
        token?.let {
            val postCrop = cropService.createCrop("Bearer $it", newCrop)

            postCrop.enqueue(object : Callback<NewCrop> {
                override fun onResponse(call: Call<NewCrop>, response: Response<NewCrop>) {
                    if (response.isSuccessful) {
                        val createdNewCrop = response.body()
                        if (createdNewCrop != null) {
                            // Map NewCrop to Crop (assuming you get some required fields from the server)
                            val crop = Crop(
                                id = "", // Actual ID from the server or generated
                                name = createdNewCrop.name,
                                author = createdNewCrop.author,
                                state = "", // Adjust as needed
                                phase = "", // Adjust as needed
                                startDate = "" // Adjust as needed
                            )
                            callback(crop)
                        }
                    } else {
                        Log.d("CropRepository", "Failed to create crop: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<NewCrop>, t: Throwable) {
                    Log.e("CropRepository", "Failed to create crop", t)
                }
            })
        } ?: run {
            Log.e("CropRepository", "Token is null or empty")
        }
    }

    fun patchCrop(id: String, updateCrop: UpdateCrop, callback: (Crop) -> Unit){
        if (updateCrop.phase == "harvest"){
            updateCrop.state = false
        }

        token?.let {
            val toUpdateCrop = cropService.patchCrop("Bearer $it", id, updateCrop)

            toUpdateCrop.enqueue(object : Callback<Crop> {
                override fun onResponse(call: Call<Crop>, response: Response<Crop>) {
                    if (response.isSuccessful) {
                        response.body()?.let { crop ->
                            callback(crop)
                        }
                    }
                }

                override fun onFailure(p0: Call<Crop>, p1: Throwable) {
                    p1.message?.let { message ->
                        Log.d("CropRepository", message)
                    }
                }

            })
        }
    }
}

