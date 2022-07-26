package data.zohre.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.database.RestaurantEntity


@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    suspend fun insertRestaurant(restaurantEntity: RestaurantEntity)

    @Query("DELETE FROM restaurant_table WHERE restaurant_name = :name")
    suspend fun deleteByRestaurantName(name: String)

    @Query("SELECT * FROM restaurant_table")
    fun getRestaurants(): Flow<List<RestaurantEntity>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines
}