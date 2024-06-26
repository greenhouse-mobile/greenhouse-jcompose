package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.factories.CompanyServiceFactory
import com.integradis.greenhouse.model.data.company.CompanyData
import com.integradis.greenhouse.model.remote.company.CompanyService
import com.integradis.greenhouse.shared.SharedPreferencesHelper

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CompanyRepository(
    private val companyService: CompanyService = CompanyServiceFactory.getCompanyService(),
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

        private val token: String?
            get() = sharedPreferencesHelper.getToken()

        fun getCompany(endpoint: String, callback: (CompanyData?) -> Unit) {
            token?.let {
                val getCompany = companyService.getCompany("Bearer $it", endpoint)

                getCompany.enqueue(object : Callback<CompanyData> {
                    override fun onResponse(
                        call: Call<CompanyData>,
                        response: Response<CompanyData>
                    ) {
                        if (response.isSuccessful) {
                            callback(response.body())
                        }
                    }

                    override fun onFailure(
                        call: Call<CompanyData>,
                        t: Throwable
                    ) {
                        t.message?.let { message ->
                            Log.d("CompanyRepository", message)
                        }
                    }
                })
            }
        }
}