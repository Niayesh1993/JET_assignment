package data.zohre.data.api.response

import com.google.gson.annotations.SerializedName
import data.zohre.data.api.model.RestaurantsDto

data class RestaurantResponse(
    @SerializedName("restaurants") val restaurants : List<RestaurantsDto>
)
