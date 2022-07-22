package data.zohre.data.repository

import android.content.Context
import data.zohre.data.datasource.RestaurantsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RestaurantsRepository
import javax.inject.Inject


class RestaurantsRepositoryImpl@Inject constructor(
    private val remoteDataSource: RestaurantsRemoteDataSource
) : RestaurantsRepository {

    override suspend fun getRestaurants(context: Context): JsonResult<List<Restaurants>> = withContext(Dispatchers.IO){
        return@withContext remoteDataSource.fetchRestaurants(context)
    }

    override fun sortRestaurant(sortStatus: String): List<Restaurants> {
        return remoteDataSource.sortRestaurant(sortStatus)
    }
}