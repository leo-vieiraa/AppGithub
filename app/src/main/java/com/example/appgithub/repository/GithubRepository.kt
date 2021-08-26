package com.example.appgithub.repository

import android.content.Context
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

}