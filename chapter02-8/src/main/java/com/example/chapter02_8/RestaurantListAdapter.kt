package com.example.chapter02_8

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter02_8.databinding.ItemRestaurantBinding
import com.naver.maps.geometry.LatLng

class RestaurantListAdapter(private val onClick: (LatLng) -> Unit) :
    RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    private var dataSet = emptyList<SearchItem>()

    inner class ViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchItem) {
            binding.titleTextView.text = item.title
            binding.categoryTextView.text = item.category
            binding.locationTextView.text = item.roadAddress

            binding.root.setOnClickListener {
                onClick(LatLng(item.mapy.toDouble() / 10000000, item.mapx.toDouble() / 10000000))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<SearchItem>) {
        this.dataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}