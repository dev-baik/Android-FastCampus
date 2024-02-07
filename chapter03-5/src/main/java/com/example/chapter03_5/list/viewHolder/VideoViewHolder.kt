package com.example.chapter03_5.list.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.chapter03_5.databinding.ItemVideoBinding
import com.example.chapter03_5.model.ListItem
import com.example.chapter03_5.model.VideoItem

class VideoViewHolder(
    private val binding: ItemVideoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as VideoItem
        binding.item = item
    }
}