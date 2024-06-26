package com.integradis.greenhouse.factories

import com.integradis.greenhouse.model.remote.company.CompanyService
import com.integradis.greenhouse.network.ApiClient

object CompanyServiceFactory {
    fun getCompanyService(): CompanyService {
        return ApiClient.getRetrofit().create(CompanyService::class.java)
    }
}