package com.zohre.jetapplication.ui.restaurantonList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.zohre.domain.model.Restaurants
import xyz.zohre.domain.usecase.GetRestaurantsUseCase
import xyz.zohre.domain.usecase.SortRestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val sortRestaurantUseCase: SortRestaurantUseCase
    ): ViewModel()
{
    private val _restaurants = MutableLiveData<List<Restaurants>>()
    val restaurants: LiveData<List<Restaurants>> get() = _restaurants

    fun loadRestaurants(context: Context, status: String) {
        viewModelScope.launch {
            val result =
                runCatching { getRestaurantsUseCase.execute(context) }
            result.onSuccess {
                sortRestaurantBy(status)
            }.onFailure {

            }
        }
    }

    fun sortRestaurantBy(sortStatus: String) {

        _restaurants.value = sortRestaurantUseCase.sortRestaurant(sortStatus)
    }

}