package com.example.appgithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appgithub.R
import com.example.appgithub.databinding.RepoItemFragmentBinding
import com.example.appgithub.model.Repo

class RepoListAdapter() : RecyclerView.Adapter<RepoListViewHolder>(){

    private val repoList = mutableListOf<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_fragment, parent, false)
        return RepoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        repoList[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = repoList.size

    fun update(newlist: List<Repo>) {
        repoList.clear()
        repoList.addAll(newlist)
        notifyDataSetChanged()
    }

}

class RepoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding : RepoItemFragmentBinding = RepoItemFragmentBinding.bind(view)

    fun bind(repoItem: Repo) {

        binding.nameTextView.text = repoItem.name

    }

}