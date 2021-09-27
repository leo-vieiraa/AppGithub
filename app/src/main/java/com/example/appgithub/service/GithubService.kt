package com.example.appgithub.service

import com.example.appgithub.model.PullRequest
import com.example.appgithub.model.RepoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GithubService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepos() : Call<RepoResponse>

    @GET("/search/repositories")
    suspend fun getRepos(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ) : Response<RepoResponse>

    @GET
    suspend fun getPullRequest(@Url url: String) : Response<List<PullRequest>>

}