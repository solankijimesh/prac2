package com.example.mvvm.network

import com.example.mvvm.model.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users?")
    fun getUsersDetails(@Query("page") page: Int,  @Query("per_page") pageSize: Int): Call<UserResponse>

}