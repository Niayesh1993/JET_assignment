package data.zohre.data.repository

import xyz.zohre.domain.model.database.RestaurantEntity
import data.zohre.data.datasource.local.RestaurantLocalDataSource
import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.LocalRestaurantsRepository
import javax.inject.Inject

class LocalRestaurantsRepositoryImpl@Inject constructor(
    private val restaurantLocalDataSource: RestaurantLocalDataSource
) : LocalRestaurantsRepository {

    override suspend fun fetchRestaurants(): Flow<List<RestaurantEntity>> {
        return restaurantLocalDataSource.fetchRestaurants()
    }

    override suspend fun insertRestaurant(restaurants: Restaurants) {
        restaurantLocalDataSource.insertRestaurant(restaurants)
    }

    override suspend fun deleteRestaurant(name: String) {
        restaurantLocalDataSource.deleteRestaurant(name)
    }
}