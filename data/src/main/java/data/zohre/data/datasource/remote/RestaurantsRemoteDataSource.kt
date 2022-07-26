package data.zohre.data.datasource.remote

import android.content.Context
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants

interface RestaurantsRemoteDataSource {

    suspend fun fetchRestaurants(context: Context): JsonResult<List<Restaurants>>

}