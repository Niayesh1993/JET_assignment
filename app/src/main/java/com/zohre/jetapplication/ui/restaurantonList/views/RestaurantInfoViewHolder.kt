package com.zohre.jetapplication.ui.restaurantonList.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zohre.jetapplication.R
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

     fun onBind(
          restaurant: Restaurants,
          onItemClickListener: (Restaurants) -> Unit,
          favoriteOnItemClickListener: (Restaurants) -> Unit
     ) {
          binding.name.text = restaurant.name
          binding.status.text = restaurant.status
          binding.distance.text = restaurant.sortingValues.distance.toString()
          binding.ratingAverage.text = restaurant.sortingValues.ratingAverage.toString()
          if (restaurant.isFavorite) {
               binding.favoriteIcon.setImageResource(R.drawable.ic_favorite)
          }
          else {
               binding.favoriteIcon.setImageResource(R.drawable.ic_un_favorite)
          }
          binding.root.setOnClickListener {
               onItemClickListener(restaurant)
          }
          binding.favoriteIcon.setOnClickListener{
               if (restaurant.isFavorite) {
                    restaurant.isFavorite = false
                    binding.favoriteIcon.setImageResource(R.drawable.ic_un_favorite)
                    favoriteOnItemClickListener(restaurant)
               }
               else if (!restaurant.isFavorite) {
                    restaurant.isFavorite = true
                    binding.favoriteIcon.setImageResource(R.drawable.ic_favorite)
                    favoriteOnItemClickListener(restaurant)

               }

          }

     }
}