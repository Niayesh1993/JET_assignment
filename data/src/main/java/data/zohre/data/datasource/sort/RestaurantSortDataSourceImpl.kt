package data.zohre.data.datasource.sort

import data.zohre.data.datasource.remote.RestaurantsRemoteDataSourceImpl
import xyz.zohre.domain.model.Restaurants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantSortDataSourceImpl@Inject constructor(): RestaurantSortDataSource {

    private val favoriteOrder = mapOf("true" to 2, "false" to 1)
    private val statusOrder = mapOf("open" to 3, "order ahead" to 2, "closed" to 1)

    override fun sortRestaurant(
        sortStatus: String,
        restaurants: List<Restaurants>,
    ): List<Restaurants> {
        when (sortStatus) {
            RestaurantsRemoteDataSourceImpl.GROUP_BY_BEST_MATCH -> {
                return restaurants.sortedWith(compareBy({favoriteOrder[it.isFavorite.toString()]}, {statusOrder[it.status]}, {it.sortingValues.bestMatch})).reversed()
            }
            RestaurantsRemoteDataSourceImpl.GROUP_BY_NEWEST -> {
                return restaurants.sortedWith(compareBy({favoriteOrder[it.isFavorite.toString()]}, {statusOrder[it.status]}, {it.sortingValues.newest})).reversed()
            }
            RestaurantsRemoteDataSourceImpl.GROUP_BY_RATING -> {
                return restaurants.sortedWith(compareBy({favoriteOrder[it.isFavorite.toString()]}, {statusOrder[it.status]}, {it.sortingValues.ratingAverage})).reversed()
            }
            RestaurantsRemoteDataSourceImpl.GROUP_BY_DISTANCE -> {
                return restaurants.sortedWith(compareBy({favoriteOrder[it.isFavorite.toString()]}, {statusOrder[it.status]}, {it.sortingValues.distance})).reversed()
            }
            RestaurantsRemoteDataSourceImpl.GROUP_BY_POPULARITY -> {
                return restaurants.sortedWith(compareBy({favoriteOrder[it.isFavorite.toString()]}, {statusOrder[it.status]}, {it.sortingValues.popularity})).reversed()
            }
        }
        return restaurants
    }
}