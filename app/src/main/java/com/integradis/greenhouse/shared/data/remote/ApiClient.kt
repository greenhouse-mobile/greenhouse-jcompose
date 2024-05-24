package com.integradis.greenhouse.shared.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //private const val BASE_URL = "http://4.236.191.92:3000/"

    private const val BASE_URL = "http://192.168.0.7:3001/" // si se usa json-server local, colocar el IP tu equipo como BASE_URL

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}