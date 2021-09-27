package com.example.appgithub.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgithub.R
import com.example.appgithub.adapter.RepoListAdapter
import com.example.appgithub.databinding.RepoListFragmentBinding
import com.example.appgithub.model.Repo
import com.example.appgithub.viewmodel.RepoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.repo_list_fragment) {

    companion object {
        fun newInstance() = RepoListFragment()
    }

    private lateinit var viewModel: RepoListViewModel
    private lateinit var binding: RepoListFragmentBinding
    private val adapter = RepoListAdapter { repo ->

        val bundle = Bundle()
        bundle.putSerializable("repo", repo)

        val fragment = PRFragment.newInstance()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager
            .beginTransaction()
            .hide(this)
            .add(R.id.container, fragment)
            .addToBackStack("repos")
            .commit()
    }

    private val observerRepos = Observer<List<Repo>> { repos ->
        adapter.update(repos)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = RepoListFragmentBinding.bind(view)
        binding.repoListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.repoListRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
        viewModel.repo.observe(viewLifecycleOwner, observerRepos)

        viewModel.getRepositories()

    }

}