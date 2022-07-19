package xyz.zohre.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants

/**
 * The implementation of this interface should be responsible for providing restaurants information
 * to use cases and should be implemented in the data layer.
 */
interface RestaurantsRepository {
    /**
     * It returns Result.success(listOf(restaurants)) when restaurants are available.
     * It returns Result.success(emptyList()) when there are no restaurants available.
     * It returns Result.failure() when it cannot be handled at the moment.
     */
    suspend fun getRestaurants(context: Context, fileName: String): JsonResult<List<Restaurants>>


}