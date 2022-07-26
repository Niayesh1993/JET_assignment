package xyz.zohre.domain.repository

import android.content.Context
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants

/**
 * The implementation of this interface should be responsible for providing restaurants information
 * to use cases and should be implemented in the data layer.
 */
interface RemoteRestaurantsRepository {

    suspend fun getRestaurants(context: Context): JsonResult<List<Restaurants>>

    fun sortRestaurant(sortStatus: String, restaurants: List<Restaurants>): List<Restaurants>

}