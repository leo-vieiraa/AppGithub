package com.example.appgithub.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repo (
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("owner")
    val owner: User,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("forks_count")
    val forks: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("pulls_url")
    val pullsUrl: String
) : Serializable

data class RepoResponse (

    @SerializedName("items")
    val results : List<Repo>
        )