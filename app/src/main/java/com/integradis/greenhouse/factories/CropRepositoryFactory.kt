package com.integradis.greenhouse.factories

import com.integradis.greenhouse.repositories.CropRepository
import com.integradis.greenhouse.shared.SharedPreferencesHelper

class CropRepositoryFactory private constructor() {
    companion object {
        private var cropRepository: CropRepository? = null

        fun getCropRepository(sharedPreferencesHelper: SharedPreferencesHelper): CropRepository {
            if (cropRepository == null) {
                cropRepository = CropRepository(sharedPreferencesHelper = sharedPreferencesHelper)
            }
            return cropRepository as CropRepository
        }
    }
}
