package xyz.zohre.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RemoteRestaurantsRepository
import javax.inject.Inject

@ViewModelScoped
class SortRestaurantUseCase@Inject constructor(
    private val repository: RemoteRestaurantsRepository
) {
     fun sortRestaurant(sortStatus: String, restaurants: List<Restaurants>): List<Restaurants> =
        repository.sortRestaurant(sortStatus, restaurants)
}