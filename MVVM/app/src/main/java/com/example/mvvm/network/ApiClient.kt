package com.example.mvvm.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(15, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        .build()

    var apiService: ApiService? = null

    fun getClient(): ApiService {

        if (apiService == null) {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService!!
    }
}