package com.example.appgithub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgithub.model.Repo
import com.example.appgithub.model.RepoResponse
import com.example.appgithub.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    val _repo = MutableLiveData<List<Repo>>()
    val repo : LiveData<List<Repo>> = _repo

    val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getRepositories() {
        viewModelScope.launch {
            val responseModel = repository.getRepositories().await().let {
                _repo.value = it?.results
            }
            println(responseModel)
        }
    }
}