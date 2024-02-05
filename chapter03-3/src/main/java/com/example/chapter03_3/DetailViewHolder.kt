package com.example.chapter03_3

import androidx.recyclerview.widget.RecyclerView
import com.example.chapter03_3.databinding.ItemDetailBinding

class DetailViewHolder(private val binding: ItemDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DetailItem) {
        binding.item = item
    }
}