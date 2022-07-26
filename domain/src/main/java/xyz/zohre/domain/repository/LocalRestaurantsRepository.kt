package xyz.zohre.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.model.database.RestaurantEntity


/**
 * The implementation of this interface should be responsible for providing restaurants information
 * to use cases and should be implemented in the data layer.
 */
interface LocalRestaurantsRepository {

    suspend fun fetchRestaurants(): Flow<List<RestaurantEntity>>

    suspend fun insertRestaurant(restaurants: Restaurants)

    suspend fun deleteRestaurant(name: String)
}