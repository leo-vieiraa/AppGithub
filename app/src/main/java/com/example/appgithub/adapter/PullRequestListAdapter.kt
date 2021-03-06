package com.example.appgithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.appgithub.R
import com.example.appgithub.databinding.PrItemFragmentBinding
import com.example.appgithub.model.PullRequest

class PullRequestListAdapter(
    val closureClick: (PullRequest) -> Unit
) : RecyclerView.Adapter<PullRequestListViewHolder>() {

    private val pullRequestList = mutableListOf<PullRequest>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pr_item_fragment, parent, false)
        return PullRequestListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestListViewHolder, position: Int) {
        pullRequestList[position].apply {
            holder.bind(this)
        }
        holder.itemView.setOnClickListener {
            closureClick(pullRequestList[position])
        }
    }

    override fun getItemCount(): Int = pullRequestList.size

    fun update(newList: List<PullRequest>) {
        pullRequestList.clear()
        pullRequestList.addAll(newList)
        notifyDataSetChanged()
    }

}

class PullRequestListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = PrItemFragmentBinding.bind(view)

    fun bind(pullRequest: PullRequest) {

        binding.titlePullRequestTextView.text = pullRequest.title
        binding.bodyPullRequestTextView.text = pullRequest.body
        binding.createdDateTextView.text = pullRequest.createdAt
        binding.contributorNameTextView.text = pullRequest.user.login

        val radius = itemView.context.resources.getDimensionPixelSize(R.dimen.corner_radius)
        pullRequest.user.apply {
            Glide.with(itemView.context)
                .load(this.avatar)
                .transform(RoundedCorners(radius))
                .placeholder(R.drawable.img)
                .into(binding.avatarImageView)
        }

    }

}