package com.example.appgithub.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PullRequest(
    val id: Int,
    val user: User,
    @SerializedName("body")
    val body: String,
    val title: String,
    @SerializedName("created_at")
    val createdAt: String
) : Serializable
