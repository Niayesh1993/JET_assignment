package xyz.zohre.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import xyz.zohre.domain.repository.LocalRestaurantsRepository
import javax.inject.Inject

@ViewModelScoped
class DeleteFavoriteRestaurantUseCase@Inject constructor(
    private val repository: LocalRestaurantsRepository
) {
    suspend fun deleteRestaurant(name: String) =
        repository.deleteRestaurant(name)
}