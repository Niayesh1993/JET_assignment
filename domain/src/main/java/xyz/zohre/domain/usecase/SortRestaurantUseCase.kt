package xyz.zohre.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RestaurantsRepository
import javax.inject.Inject

@ViewModelScoped
class SortRestaurantUseCase@Inject constructor(
    private val repository: RestaurantsRepository
) {
     fun sortRestaurant(sortStatus: String): List<Restaurants> =
        repository.sortRestaurant(sortStatus)
}