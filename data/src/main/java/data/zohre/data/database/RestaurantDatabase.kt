package data.zohre.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.zohre.domain.model.database.RestaurantEntity


@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantDatabase: RoomDatabase() {

    abstract fun getRestaurantDao(): RestaurantDao

    companion object {
        private const val DB_NAME = "restaurant_database.db"
        @Volatile private var instance: RestaurantDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RestaurantDatabase::class.java,
            DB_NAME
        ).build()
    }
}