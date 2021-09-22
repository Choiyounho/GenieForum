package com.soten.genieforum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.soten.genieforum.R
import com.soten.genieforum.databinding.ItemOnBoardingBinding

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    private val items: List<Int> = listOf(
        R.drawable.on_boarding,
        R.drawable.on_boarding,
        R.drawable.on_boarding,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemOnBoardingBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val binding: ItemOnBoardingBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(@DrawableRes resId: Int) {
            binding.image.setImageResource(resId)
        }
    }
}

