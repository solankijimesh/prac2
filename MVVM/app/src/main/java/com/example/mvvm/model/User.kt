package com.example.mvvm.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("first_name")
    val firstName: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("avatar")
    val avatar: String? = null,
)