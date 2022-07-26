package data.zohre.data.datasource.local

import data.zohre.data.database.RestaurantDatabase
import data.zohre.data.database.adapter.convertToRestaurantEntity
import data.zohre.data.datasource.local.RestaurantLocalDataSource
import xyz.zohre.domain.model.database.RestaurantEntity
import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.Restaurants
import javax.inject.Inject

class RestaurantLocalDataSourceImpl@Inject constructor(private val restaurantDatabase: RestaurantDatabase):
    RestaurantLocalDataSource {

    override suspend fun insertRestaurant(restaurants: Restaurants) {
        val restaurantEntity = restaurants.convertToRestaurantEntity()
        restaurantDatabase.getRestaurantDao().insertRestaurant(restaurantEntity)
    }

    override suspend fun deleteRestaurant(name: String) {
        restaurantDatabase.getRestaurantDao().deleteByRestaurantName(name)
    }

    override fun fetchRestaurants(): Flow<List<RestaurantEntity>> {
        return restaurantDatabase.getRestaurantDao().getRestaurants()
    }

}