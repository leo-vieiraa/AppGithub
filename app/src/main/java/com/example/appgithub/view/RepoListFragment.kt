package com.example.appgithub.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgithub.R
import com.example.appgithub.adapter.RepoListAdapter
import com.example.appgithub.databinding.RepoListFragmentBinding
import com.example.appgithub.interfaces.ClickableItem
import com.example.appgithub.model.Repo
import com.example.appgithub.model.RepoResponse
import com.example.appgithub.viewmodel.RepoListViewModel

class RepoListFragment : Fragment(R.layout.repo_list_fragment),ClickableItem {

    companion object {
        fun newInstance() = RepoListFragment()
    }

    private lateinit var viewModel: RepoListViewModel
    private lateinit var binding: RepoListFragmentBinding
    private val adapter = RepoListAdapter(this)

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

        viewModel.fetchAllFromServer(requireContext())

    }

    override fun onClickGoToDetail(repo: Repo) {
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

}