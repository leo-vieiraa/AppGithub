package com.example.appgithub.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val retrofitFake = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getGithubService() : GithubService {
        return retrofitFake.create(GithubService::class.java)
    }

}