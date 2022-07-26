package data.zohre.data.datasource.sort

import xyz.zohre.domain.model.Restaurants

interface RestaurantSortDataSource {

    fun sortRestaurant(sortStatus: String, restaurants: List<Restaurants>): List<Restaurants>
}