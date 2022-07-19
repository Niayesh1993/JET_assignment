package xyz.zohre.domain.usecase

import android.content.Context
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import xyz.zohre.domain.model.JsonResult
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.repository.RestaurantsRepository
import javax.inject.Inject

@ViewModelScoped
class GetRestaurantsUseCase@Inject constructor(
    private val repository: RestaurantsRepository
) {
    suspend fun execute(context: Context, fileName: String): JsonResult<List<Restaurants>> =
        repository.getRestaurants(context, fileName)
}