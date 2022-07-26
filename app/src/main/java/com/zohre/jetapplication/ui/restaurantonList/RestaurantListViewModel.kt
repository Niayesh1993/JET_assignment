package com.zohre.jetapplication.ui.restaurantonList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.zohre.data.database.adapter.convertToRestaurants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.usecase.*
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val sortRestaurantUseCase: SortRestaurantUseCase,
    private val saveFavoriteRestaurantUseCase: SaveFavoriteRestaurantUseCase,
    private val fetchLocalRestaurantsUseCase: FetchLocalRestaurantsUseCase,
    private val deleteFavoriteRestaurantUseCase: DeleteFavoriteRestaurantUseCase
    ): ViewModel()
{
    private val _restaurants = MutableLiveData<List<Restaurants>>()
    val restaurants: LiveData<List<Restaurants>> get() = _restaurants

    private lateinit var unsortedRestaurant: List<Restaurants>

    private lateinit var favoriteRestaurants: List<Restaurants>

    fun loadRestaurants(context: Context, status: String) {
        viewModelScope.launch {
            val result =
                runCatching { getRestaurantsUseCase.execute(context) }
            result.onSuccess {
                unsortedRestaurant = it.value!!
                loadFavoriteRestaurant(status)
            }.onFailure {

            }
        }
    }

    private fun loadFavoriteRestaurant(status: String) {
        viewModelScope.launch {
            fetchLocalRestaurantsUseCase.fetchRestaurants().collectLatest {
                favoriteRestaurants = it.convertToRestaurants()
                sortRestaurantBy(status)
            }
        }
    }

    fun sortRestaurantBy(sortStatus: String) {
        var mergedList = favoriteRestaurants + unsortedRestaurant
        mergedList = mergedList.distinctBy { it.name }
        _restaurants.value = sortRestaurantUseCase.sortRestaurant(sortStatus, mergedList)
    }

    fun updateFavoriteRestaurant(restaurants: Restaurants) {

        viewModelScope.launch {
            if (restaurants.isFavorite) saveFavoriteRestaurantUseCase.saveRestaurant(restaurants)
            else deleteFavoriteRestaurantUseCase.deleteRestaurant(restaurants.name)
        }
    }

}