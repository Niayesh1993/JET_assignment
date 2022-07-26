package data.zohre.data.datasource.local

import xyz.zohre.domain.model.database.RestaurantEntity
import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.Restaurants

interface RestaurantLocalDataSource {

    suspend fun insertRestaurant(restaurants: Restaurants)

    suspend fun deleteRestaurant(name: String)

     fun fetchRestaurants(): Flow<List<RestaurantEntity>>
}