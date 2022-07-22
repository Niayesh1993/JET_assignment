package data.zohre.data.datasource

import android.content.Context
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants

interface RestaurantsRemoteDataSource {

    suspend fun fetchRestaurants(context: Context): JsonResult<List<Restaurants>>

    fun sortRestaurant(sortStatus: String): List<Restaurants>

}