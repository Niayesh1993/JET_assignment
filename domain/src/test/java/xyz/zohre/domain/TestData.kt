package xyz.zohre.domain

import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.model.SortingValues

object TestData {

    val list = mutableListOf(
        mockRestaurant(1, "aaa", "open", mockSortingValue(1, "12", "20","1.0", "123", "25", "1200", "5620", "25"), "true"),
        mockRestaurant(2, "bbb", "close", mockSortingValue(2, "25", "0.0", "2.5", "56.21", "45", "5230", "4520", "523"), "false"),
        mockRestaurant(3, "kkk", "order ahead", mockSortingValue(3, "4.0", "86", "3.0", "45.20", "3.2", "5200", "1200", "542"), "true")

    )

    private fun mockRestaurant(
        mockId: Int,
        name: String = "name$mockId",
        status: String = "status$mockId",
        sortingValues: SortingValues = mockSortingValue(mockId),
        isFavorite: String = "isFavorite$mockId"
        ): Restaurants {
        return Restaurants(
            name = name,
            status = status,
            sortingValues = sortingValues,
            isFavorite = isFavorite.toBoolean()
        )
    }

    private fun mockSortingValue(
        mockId: Int,
        bestMatch: String = "bestMatch$mockId",
        newest: String = "newest$mockId",
        ratingAverage: String = "ratingAverage$mockId",
        distance: String = "distance$mockId",
        popularity: String = "popularity$mockId",
        averageProductPrice: String = "averageProductPrice$mockId",
        deliveryCosts: String = "deliveryCosts$mockId",
        minCost: String = "minCost$mockId"
    ): SortingValues {
        return SortingValues(
            bestMatch = bestMatch.toDouble(),
            newest = newest.toDouble(),
            ratingAverage = ratingAverage.toDouble(),
            distance = distance.toDouble(),
            popularity = popularity.toDouble(),
            averageProductPrice = averageProductPrice.toDouble(),
            deliveryCosts = deliveryCosts.toDouble(),
            minCost = minCost.toDouble()
        )
    }
}