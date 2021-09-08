package com.example.appgithub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgithub.model.PullRequest
import com.example.appgithub.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PRViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    val _pullRequest = MutableLiveData<List<PullRequest>>()
    val pullRequest : LiveData<List<PullRequest>> = _pullRequest

    val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPullRequest(url: String) {
        viewModelScope.launch {
            val responseModel = repository.getPullRequests(url).await()
            println(responseModel)
        }
    }

}