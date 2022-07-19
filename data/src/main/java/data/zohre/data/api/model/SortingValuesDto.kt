package data.zohre.data.api.model

import com.google.gson.annotations.SerializedName

data class SortingValuesDto(
    @SerializedName("bestMatch") val bestMatch: Double,
    @SerializedName("newest") val newest: Double,
    @SerializedName("ratingAverage") val ratingAverage: Double,
    @SerializedName("distance") val distance: Double,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("averageProductPrice") val averageProductPrice: Double,
    @SerializedName("deliveryCosts") val deliveryCosts: Double,
    @SerializedName("minCost") val minCost: Double
)
