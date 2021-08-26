package com.example.appgithub.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appgithub.R
import com.example.appgithub.viewmodel.RepoListViewModel

class RepoListFragment : Fragment() {

    companion object {
        fun newInstance() = RepoListFragment()
    }

    private lateinit var viewModel: RepoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}