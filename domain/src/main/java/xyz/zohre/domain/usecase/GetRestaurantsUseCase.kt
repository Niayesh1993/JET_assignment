package xyz.zohre.domain.usecase

import android.content.Context
import dagger.hilt.android.scopes.ViewModelScoped
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RemoteRestaurantsRepository
import javax.inject.Inject

@ViewModelScoped
class GetRestaurantsUseCase@Inject constructor(
    private val repository: RemoteRestaurantsRepository
) {
    suspend fun execute(context: Context): JsonResult<List<Restaurants>> =
        repository.getRestaurants(context)
}