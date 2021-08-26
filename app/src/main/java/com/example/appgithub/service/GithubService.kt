package com.example.appgithub.service

import com.example.appgithub.model.Repo
import com.example.appgithub.model.RepoResponse
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepos() : Call<RepoResponse>

}