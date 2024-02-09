package com.example.chapter03_6.viewholder

import com.example.chapter03_6.ListAdapter
import com.example.chapter03_6.databinding.ItemViewpagerBinding
import com.example.chapter03_6.model.ListItem
import com.example.chapter03_6.model.ViewPager

class ViewPagerViewHolder(
    binding: ItemViewpagerBinding
) : BindingViewHolder<ItemViewpagerBinding>(binding) {

    private val adapter = ListAdapter()

    init {
        binding.viewpager.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as ViewPager
        adapter.submitList(item.items)
    }
}