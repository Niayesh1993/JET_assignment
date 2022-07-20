package data.zohre.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

    override suspend fun fetchRestaurants(context: Context, fileName: String): JsonResult<List<Restaurants>> = withContext(
        Dispatchers.IO){
        val jsonFileStringDeferred = async { getJsonDataFromAsset(context, fileName) }
        val jsonFileString = jsonFileStringDeferred.await()

        val gson = Gson()
        val listRestaurantType = object : TypeToken<RestaurantResponse>() {}.type

        val products: RestaurantResponse = gson.fromJson(jsonFileString, listRestaurantType)
        products.restaurants.forEachIndexed{idx, restaurant -> println("> Item ${idx}: \n${restaurant}")}

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
}
