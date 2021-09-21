package com.soten.genieforum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.genieforum.R
import com.soten.genieforum.databinding.ForumBinding
import com.soten.genieforum.domain.model.Forum
import com.soten.genieforum.domain.model.Topic

class ForumAdapter(
    private val itemClickListener: (Forum) -> Unit
) : ListAdapter<Forum, ForumAdapter.ForumViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ForumAdapter.ForumViewHolder {
        return ForumViewHolder(
            ForumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForumAdapter.ForumViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ForumViewHolder(val binding: ForumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forum: Forum) {
            binding.forum = forum

            when(forum.topic) {
                Topic.FREE.name -> binding.topicImage.load(R.drawable.ic_free)
                Topic.EXERCISE.name -> binding.topicImage.load(R.drawable.ic_exercise)
                Topic.TRIP.name -> binding.topicImage.load(R.drawable.ic_trip)
                Topic.LOVE.name -> binding.topicImage.load(R.drawable.ic_love)
            }

            binding.root.setOnClickListener {
                itemClickListener.invoke(forum)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Forum>() {
            override fun areItemsTheSame(oldItem: Forum, newItem: Forum) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Forum, newItem: Forum) = oldItem == newItem
        }
    }
}