package com.example.appgithub.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgithub.R
import com.example.appgithub.adapter.PullRequestListAdapter
import com.example.appgithub.databinding.PrFragmentBinding
import com.example.appgithub.databinding.PrItemFragmentBinding
import com.example.appgithub.model.PullRequest
import com.example.appgithub.model.Repo
import com.example.appgithub.viewmodel.PRViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PRFragment : Fragment(R.layout.pr_fragment) {

    companion object {
        fun newInstance() = PRFragment()
    }

    private lateinit var viewModel: PRViewModel
    private lateinit var binding: PrFragmentBinding
    private val adapter = PullRequestListAdapter { it ->
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))
        startActivity(browserIntent)
    }

    private val observerPullRequest = Observer<List<PullRequest>> { pullRequest ->
        adapter.update(pullRequest)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PrFragmentBinding.bind(view)
        binding.pullRequestRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pullRequestRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(PRViewModel::class.java)
        viewModel.pullRequest.observe(viewLifecycleOwner, observerPullRequest)

        val arguments = arguments?.getSerializable("repo") as Repo
        val url = arguments.pullsUrl.replace("https://api.github.com", "").replace("{/number}", "")

        viewModel.fetchPullRequest(url)

        binding.backButton.apply {
            setOnClickListener{
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, RepoListFragment.newInstance())
                    .commitNow()
            }
        }

    }

}