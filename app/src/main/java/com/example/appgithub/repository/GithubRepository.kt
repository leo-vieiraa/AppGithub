package com.example.appgithub.repository

import com.example.appgithub.model.PullRequest
import com.example.appgithub.model.RepoResponse
import com.example.appgithub.service.GithubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val services: GithubService
){

    suspend fun getRepositories(): Deferred<RepoResponse?> {
        return CoroutineScope(Dispatchers.Default).async {
            val response =
                services.getRepos(language = "language:Kotlin", page = 1, sort = "stars")
            processData(response)
        }
    }

    suspend fun getPullRequests(url: String) : Deferred<List<PullRequest>?> {
        return CoroutineScope(Dispatchers.Default).async {
            val response =
                services.getPullRequest(url)
            processData(response)
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

}