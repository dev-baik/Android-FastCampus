package com.example.chapter03_6.viewholder

import com.example.chapter03_6.ListAdapter
import com.example.chapter03_6.databinding.ItemHorizontalBinding
import com.example.chapter03_6.model.Horizontal
import com.example.chapter03_6.model.ListItem

class HorizontalViewHolder(
    private val binding: ItemHorizontalBinding
) : BindingViewHolder<ItemHorizontalBinding>(binding) {

    private val adapter = ListAdapter()

    init {
        binding.listView.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as Horizontal
        binding.titleTextView.text = item.title
        adapter.submitList(item.items)
    }
}