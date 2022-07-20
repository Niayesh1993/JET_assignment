package com.zohre.jetapplication.ui.restaurantonList.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.zohre.jetapplication.ui.restaurantonList.views.RestaurantInfoViewHolder
import xyz.zohre.domain.model.Restaurants


object RestaurantsInfoDiffCallback : DiffUtil.ItemCallback<Restaurants>() {
    override fun areItemsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
        return oldItem == newItem
    }
}

class RestaurantsInfoAdapter(private val onItemClickListener: (Restaurants) -> Unit) :
    ListAdapter<Restaurants, RestaurantInfoViewHolder>(RestaurantsInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantInfoViewHolder {
        return RestaurantInfoViewHolder.createFrom(parent)
    }

    override fun onBindViewHolder(holder: RestaurantInfoViewHolder, position: Int) {
        holder.onBind(getItem(position), onItemClickListener)
    }
}