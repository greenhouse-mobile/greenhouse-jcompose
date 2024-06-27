package com.integradis.greenhouse.factories

import com.integradis.greenhouse.repositories.ProfileRepository
import com.integradis.greenhouse.shared.SharedPreferencesHelper

class ProfileRepositoryFactory {
    companion object {
        private var profileRepository: ProfileRepository? = null

        fun getProfileRepository(sharedPreferencesHelper: SharedPreferencesHelper): ProfileRepository {
            if (profileRepository == null) {
                profileRepository = ProfileRepository(sharedPreferencesHelper = sharedPreferencesHelper)
            }
            return profileRepository as ProfileRepository
        }
    }
}