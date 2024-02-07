package com.example.chapter03_5.list.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.chapter03_5.databinding.ItemImageBinding
import com.example.chapter03_5.model.ImageItem
import com.example.chapter03_5.model.ListItem

class ImageViewHolder(
    private val binding: ItemImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as ImageItem
        binding.item = item
    }
}