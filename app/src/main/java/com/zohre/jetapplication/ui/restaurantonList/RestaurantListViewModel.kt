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
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase
    ): ViewModel()
{
    private val _restaurants = MutableLiveData<List<Restaurants>>()
    val restaurants: LiveData<List<Restaurants>> get() = _restaurants


    fun loadRestaurants(context: Context) {
        viewModelScope.launch {
            val result =
                runCatching { getRestaurantsUseCase.execute(context, fileName) }
            result.onSuccess {
                _restaurants.value = it.value
            }.onFailure {

            }
        }
    }

    companion object {
        const val fileName = "Sample Android.json"
    }
}