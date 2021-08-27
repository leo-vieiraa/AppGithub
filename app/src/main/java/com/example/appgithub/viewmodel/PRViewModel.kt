package com.example.appgithub.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appgithub.model.PullRequest
import com.example.appgithub.model.Repo
import com.example.appgithub.repository.GithubRepository

class PRViewModel : ViewModel() {

    val _pullRequest = MutableLiveData<List<PullRequest>>()
    val pullRequest : LiveData<List<PullRequest>> = _pullRequest

    val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPullRequest(context: Context) {
        val repository = GithubRepository(context)

        repository.fetchPullRequest() { response, error ->
            response?.apply {
                _pullRequest.value = this
            }
            error?.let {
                _error.value = it
            }
        }
    }

}