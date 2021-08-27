package com.example.appgithub.repository

import android.content.Context
import com.example.appgithub.model.PullRequest
import com.example.appgithub.model.RepoResponse
import com.example.appgithub.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepository (private val context: Context){

    val service = RetrofitService.getGithubService()

    fun fetchAll (onComplete: (RepoResponse?, String?) -> Unit) {

        val call = service.getRepos()

        call.enqueue(object : Callback<RepoResponse> {
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Nenhum repositório encontrado")
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                onComplete(null, t.message)
            }
        })

    }

    fun fetchPullRequest(onComplete: (List<PullRequest>?, String?) -> Unit) {

        val call = service.getPullRequest()

        call.enqueue(object : Callback<List<PullRequest>> {

            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Nenhum pull request encontrado")
                }
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                onComplete(null, t.message)
            }
        })

    }

}