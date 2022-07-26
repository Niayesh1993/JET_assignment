package data.zohre.data.database.adapter

import xyz.zohre.domain.model.database.RestaurantEntity
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.model.SortingValues


fun List<RestaurantEntity>.convertToRestaurants(): List<Restaurants> {

    return this.map {
        Restaurants(
            it.name,
            it.status,
            SortingValues(
                it.bestMatch,
                it.newest,
                it.ratingAverage,
                it.distance,
                it.popularity,
                it.averageProductPrice,
                it.deliveryCosts,
                it.minCost
            ),
            it.isFavorite
        )
    }
}


fun Restaurants.convertToRestaurantEntity(): RestaurantEntity {
    return RestaurantEntity(null,
        this.name,
        this.status,
        this.isFavorite,
        this.sortingValues.bestMatch,
        this.sortingValues.newest,
        this.sortingValues.ratingAverage,
        this.sortingValues.distance,
        this.sortingValues.popularity,
        this.sortingValues.averageProductPrice,
        this.sortingValues.deliveryCosts,
        this.sortingValues.minCost
    )
}