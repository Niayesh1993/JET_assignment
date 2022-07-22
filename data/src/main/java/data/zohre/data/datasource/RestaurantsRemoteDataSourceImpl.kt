package data.zohre.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.zohre.data.datasource.RestaurantsRemoteDataSourceImpl.Companion.GROUP_BY_POPULARITY
import data.zohre.data.repository.RestaurantsRepositoryImpl
import xyz.zohre.domain.model.JsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import xyz.zohre.domain.model.RestaurantResponse
import xyz.zohre.domain.model.Restaurants
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantsRemoteDataSourceImpl @Inject constructor() : RestaurantsRemoteDataSource{

    private lateinit var unSortedRestaurant: List<Restaurants>
    private val statusOrder = mapOf("open" to 3, "order ahead" to 2, "closed" to 1)

    override suspend fun fetchRestaurants(context: Context): JsonResult<List<Restaurants>> = withContext(
        Dispatchers.IO){
        val jsonFileStringDeferred = async { getJsonDataFromAsset(context, fileName) }
        val jsonFileString = jsonFileStringDeferred.await()

        val gson = Gson()
        val listRestaurantType = object : TypeToken<RestaurantResponse>() {}.type

        val products: RestaurantResponse = gson.fromJson(jsonFileString, listRestaurantType)
        products.restaurants.forEachIndexed{idx, restaurant -> println("> Item ${idx}: \n${restaurant}")}
        unSortedRestaurant = products.restaurants

        return@withContext JsonResult(products.restaurants)
    }

    //read json file from assets folder
    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {

        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun sortRestaurant(sortStatus: String): List<Restaurants> {
        when (sortStatus) {
            GROUP_BY_FAVORITE -> {}
            GROUP_BY_BEST_MATCH -> {
                return unSortedRestaurant.sortedWith(compareBy({statusOrder[it.status]}, {it.sortingValues.bestMatch})).reversed()
            }
            GROUP_BY_NEWEST -> {
                return unSortedRestaurant.sortedWith(compareBy({statusOrder[it.status]}, {it.sortingValues.newest})).reversed()
            }
            GROUP_BY_RATING -> {
                return unSortedRestaurant.sortedWith(compareBy({statusOrder[it.status]}, {it.sortingValues.ratingAverage})).reversed()
            }
            GROUP_BY_DISTANCE -> {
                return unSortedRestaurant.sortedWith(compareBy({statusOrder[it.status]}, {it.sortingValues.distance})).reversed()
            }
            GROUP_BY_POPULARITY -> {
                return unSortedRestaurant.sortedWith(compareBy({statusOrder[it.status]}, {it.sortingValues.popularity})).reversed()
            }
        }
        return unSortedRestaurant
    }

    companion object {
        const val fileName = "Sample Android.json"
        const val GROUP_BY_FAVORITE: String = "favorite"
        const val GROUP_BY_BEST_MATCH: String = "bestMatch"
        const val GROUP_BY_NEWEST: String = "newest"
        const val GROUP_BY_RATING: String = "ratingAverage"
        const val GROUP_BY_DISTANCE: String = "distance"
        const val GROUP_BY_POPULARITY: String = "popularity"
    }
}

