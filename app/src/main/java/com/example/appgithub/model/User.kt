package com.example.appgithub.model

import com.google.gson.annotations.SerializedName

data class User(
    val id : Int,
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String
)
