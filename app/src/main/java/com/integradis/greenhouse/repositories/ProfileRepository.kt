package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.factories.ProfileServiceFactory
import com.integradis.greenhouse.model.data.profile.Profile
import com.integradis.greenhouse.model.data.profile.ProfileWrapper
import com.integradis.greenhouse.model.remote.profiles.ProfileService
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository(
    private val profileService: ProfileService = ProfileServiceFactory.getProfileService(),
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    private val token: String?
        get() = sharedPreferencesHelper.getToken()

    fun getProfilesByCompanyId(companyId: String, callback: (List<Profile>) -> Unit) {
        token?.let {
            val getProfilesByCompanyId = profileService.getProfilesByCompanyId("Bearer $it", companyId)
            getProfilesByCompanyId.enqueue(object: Callback<ProfileWrapper> {
                override fun onResponse(
                    call: Call<ProfileWrapper>,
                    response: Response<ProfileWrapper>
                ) {
                    if (response.isSuccessful) {
                        val profiles = response.body()?.profiles ?: emptyList()
                        callback(profiles)
                        Log.d("ProfileRepository", "Profiles: $profiles")
                    } else {
                        Log.d("ProfileRepository", "Response not successful: ${response.code()}")
                    }
                }
                override fun onFailure(
                    call: Call<ProfileWrapper>,
                    t: Throwable
                ) {
                    Log.d("ProfileRepository", "API call failed: ${t.message}")
                }
            })
        } ?: run {
            Log.d("ProfileRepository", "Token is null")
        }
    }
}
