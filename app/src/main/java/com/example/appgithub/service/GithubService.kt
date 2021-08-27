package com.example.appgithub.service

import com.example.appgithub.model.PullRequest
import com.example.appgithub.model.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepos() : Call<RepoResponse>

//    @GET("/repos/CyC2018/CS-Notes/pulls")
//    fun getPullRequest() : Call<List<PullRequest>>

    @GET
    fun getPullRequest(@Url url: String) : Call<List<PullRequest>>

}