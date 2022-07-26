package xyz.zohre.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.LocalRestaurantsRepository
import javax.inject.Inject

@ViewModelScoped
class SaveFavoriteRestaurantUseCase@Inject constructor(
    private val repository: LocalRestaurantsRepository
) {
    suspend fun saveRestaurant(restaurants: Restaurants) =
        repository.insertRestaurant(restaurants)
}