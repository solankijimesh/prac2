package com.example.mvvm.model.response

import com.example.mvvm.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("per_page")
    val per_page: Int? = null,

    @SerializedName("total")
    val total: Int? = null,

    @SerializedName("total_pages")
    val total_pages: Int? = null,

    @SerializedName("data")
    val data: ArrayList<User>? = null,
)