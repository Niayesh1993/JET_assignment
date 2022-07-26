package xyz.zohre.domain.model.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.zohre.domain.model.SortingValues

@Entity(tableName = "restaurant_table")
data class RestaurantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @NonNull
    @ColumnInfo(name = "restaurant_name")
    val name: String,
    @NonNull
    @ColumnInfo(name = "restaurant_status")
    val status: String,
    @NonNull
    @ColumnInfo(name = "restaurant_isFavorite")
    val isFavorite: Boolean,
    @NonNull
    @ColumnInfo(name = "restaurant_bestMatch")
    val bestMatch: Double,
    @NonNull
    @ColumnInfo(name = "restaurant_newest")
    val newest: Double,
    @NonNull
    @ColumnInfo(name = "restaurant_ratingAverage")
    val ratingAverage: Double,
    @NonNull
    @ColumnInfo(name = "restaurant_distance")
    val distance: Double,
    @NonNull
    @ColumnInfo(name = "restaurant_popularity")
    val popularity: Double,
    @NonNull
    @ColumnInfo(name = "restaurant_averageProductPrice")
    val averageProductPrice: Double,
    @NonNull
    @ColumnInfo(name = "restaurant_deliveryCosts")
    val deliveryCosts: Double,
    @NonNull
    @ColumnInfo(name = "restaurant_minCost")
    val minCost: Double
)
