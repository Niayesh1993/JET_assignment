package data.zohre.data.repository

import android.content.Context
import data.zohre.data.datasource.sort.RestaurantSortDataSource
import data.zohre.data.datasource.remote.RestaurantsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RemoteRestaurantsRepository
import javax.inject.Inject


class RemoteRestaurantsRepositoryImpl@Inject constructor(
    private val remoteDataSource: RestaurantsRemoteDataSource,
    private val sortDataSource: RestaurantSortDataSource
) : RemoteRestaurantsRepository {

    override suspend fun getRestaurants(context: Context): JsonResult<List<Restaurants>> = withContext(Dispatchers.IO){
        return@withContext remoteDataSource.fetchRestaurants(context)
    }

    override fun sortRestaurant(
        sortStatus: String,
        restaurants: List<Restaurants>,
    ): List<Restaurants> {
        return sortDataSource.sortRestaurant(sortStatus, restaurants)
    }

}