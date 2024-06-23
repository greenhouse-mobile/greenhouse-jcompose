package com.integradis.greenhouse.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://ip172-18-0-38-cprlbsq91nsg00b8n2hg-3000.direct.labs.play-with-docker.com/api/v1/"
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}