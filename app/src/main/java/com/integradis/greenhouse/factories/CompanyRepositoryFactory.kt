package com.integradis.greenhouse.factories

import com.integradis.greenhouse.repositories.CompanyRepository
import com.integradis.greenhouse.shared.SharedPreferencesHelper

class CompanyRepositoryFactory private constructor() {
    companion object {
        private var companyRepository: CompanyRepository? = null;

        fun getCompanyRepository(sharedPreferencesHelper: SharedPreferencesHelper): CompanyRepository {
            if (companyRepository == null){
                companyRepository = CompanyRepository(sharedPreferencesHelper = sharedPreferencesHelper)
            }
            return companyRepository as CompanyRepository
        }
    }
}