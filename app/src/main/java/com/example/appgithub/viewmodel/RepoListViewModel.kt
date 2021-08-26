package com.example.appgithub.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appgithub.model.Repo
import com.example.appgithub.repository.GithubRepository

class RepoListViewModel : ViewModel() {

    val _repo = MutableLiveData<List<Repo>>()
    val repo : LiveData<List<Repo>> = _repo

    val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun fetchAllFromServer(context: Context) {
        val repository = GithubRepository(context)

        repository.fetchAll { response, error ->
            response?.let {
                _repo.value = it.results
            }
            error?.let {
                _error.value = it
            }
        }
    }

}