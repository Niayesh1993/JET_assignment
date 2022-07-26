package xyz.zohre.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.model.database.RestaurantEntity
import xyz.zohre.domain.repository.LocalRestaurantsRepository
import javax.inject.Inject

@ViewModelScoped
class FetchLocalRestaurantsUseCase@Inject constructor(
    private val repository: LocalRestaurantsRepository
) {
    suspend fun fetchRestaurants(): Flow<List<RestaurantEntity>> =
        repository.fetchRestaurants()
}