package com.zohre.jetapplication.ui.restaurantDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.zohre.jetapplication.R
import com.zohre.jetapplication.databinding.FragmentRestaurantDetailBinding
import com.zohre.jetapplication.databinding.FragmentRestaurantListBinding
import dagger.hilt.android.AndroidEntryPoint
import xyz.zohre.domain.model.Restaurants

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleArguments()
    }

    private fun handleArguments() {
        arguments?.getParcelable<Restaurants>("").let {
           initUI(it)
        }
    }

    private fun initUI(restaurants: Restaurants?){
        if (restaurants!= null){
            binding.name.text = restaurants.name
            binding.status.text = restaurants.status
            binding.bestMach.text = restaurants.sortingValues.bestMatch.toString()
            binding.deliveryCosts.text = restaurants.sortingValues.deliveryCosts.toString()
            binding.distance.text = restaurants.sortingValues.distance.toString()
            binding.averageProductPrice.text = restaurants.sortingValues.averageProductPrice.toString()
            binding.minCost.text = restaurants.sortingValues.minCost.toString()
            binding.newest.text = restaurants.sortingValues.newest.toString()
            binding.popularity.text = restaurants.sortingValues.popularity.toString()
        }
    }

}