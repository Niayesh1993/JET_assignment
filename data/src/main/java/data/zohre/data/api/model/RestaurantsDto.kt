package data.zohre.data.api.model

import com.google.gson.annotations.SerializedName

data class RestaurantsDto(
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("sortingValues") val sortingValues: SortingValuesDto
)
