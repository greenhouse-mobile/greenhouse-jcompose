package com.integradis.greenhouse.factories

import com.integradis.greenhouse.repositories.CropRecordRepository
import com.integradis.greenhouse.shared.SharedPreferencesHelper

class CropRecordRepositoryFactory private constructor() {
    companion object {
        private var recordRepository: CropRecordRepository? = null;

        fun getRecordRepository(sharedPreferencesHelper: SharedPreferencesHelper): CropRecordRepository {
            if (recordRepository == null){
                recordRepository = CropRecordRepository(sharedPreferencesHelper = sharedPreferencesHelper)
            }
            return recordRepository as CropRecordRepository
        }
    }
}