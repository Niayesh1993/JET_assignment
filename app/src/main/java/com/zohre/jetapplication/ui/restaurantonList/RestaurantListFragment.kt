package com.zohre.jetapplication.ui.restaurantonList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zohre.jetapplication.R
import com.zohre.jetapplication.databinding.FragmentRestaurantListBinding
import com.zohre.jetapplication.ui.restaurantonList.adapter.RestaurantsInfoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import xyz.zohre.domain.model.Restaurants


@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private lateinit var viewModel: RestaurantListViewModel

    private var _binding: FragmentRestaurantListBinding? = null
    private val binding get() = _binding!!

    private lateinit var infoAdapter: RestaurantsInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RestaurantListViewModel::class.java]

        initializeInfoList()
        initializeRestaurantObservers()
        activity?.let { viewModel.loadRestaurants(it.applicationContext) }
    }

    private fun initializeInfoList() {
        infoAdapter = RestaurantsInfoAdapter {
            showRestaurantDetail("", it)
        }
        binding.venuesInfoList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.venuesInfoList.adapter = infoAdapter


    }

    private fun initializeRestaurantObservers() {
        lifecycleScope.launch {
            viewModel.restaurants.observe(viewLifecycleOwner) {
                infoAdapter.submitList(it)
            }
        }
    }

    private fun showRestaurantDetail(key: String, restaurant: Restaurants) {
        findNavController().navigate(
            R.id.action_restaurantListFragment_to_restaurantDetailFragment,
            bundleOf(key to restaurant)
        )
    }

}