package com.zohre.jetapplication.ui.restaurantonList.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zohre.jetapplication.databinding.ItemRestaurantInfoBinding
import xyz.zohre.domain.model.Restaurants

class RestaurantInfoViewHolder private constructor(private val binding: ItemRestaurantInfoBinding):
     RecyclerView.ViewHolder(binding.root){

     companion object {
          fun createFrom(parent: ViewGroup): RestaurantInfoViewHolder {
               return RestaurantInfoViewHolder(
                    ItemRestaurantInfoBinding.inflate(
                         LayoutInflater.from(parent.context),
                         parent,
                         false
                    )
               )
          }
     }

     fun onBind(restaurant: Restaurants, onItemClickListener: (Restaurants) -> Unit) {
          binding.name.text = restaurant.name
          binding.distance.text = restaurant.sortingValues.distance.toString()
          binding.ratingAverage.text = restaurant.sortingValues.ratingAverage.toString()
          binding.root.setOnClickListener {
               onItemClickListener(restaurant)
          }
     }
}