package com.example.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.User
import com.example.mvvm.model.response.UserResponse
import com.example.mvvm.network.ApiClient
import com.example.mvvm.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel constructor() : ViewModel() {

    var userList = MutableLiveData<ArrayList<User>>()

    fun loadUsers(page: Int, pageSize: Int) {

        val apiService: ApiService = ApiClient().getClient()

        val call = apiService.getUsersDetails(page, pageSize)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {

                if (response.body()?.data != null && response.body()?.data?.size!! > 0)
                    userList.value = response.body()?.data
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Error", t.localizedMessage!!)
            }
        })
    }
}