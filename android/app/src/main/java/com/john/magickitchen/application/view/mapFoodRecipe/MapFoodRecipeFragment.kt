package com.john.magickitchen.application.view.mapFoodRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.john.magickitchen.R
import com.john.magickitchen.application.view.foodRecipeDetail.FoodRecipeDetailFragment.Companion.locationRecipe
import com.john.magickitchen.application.view.shared.BitmapHelper
import com.john.magickitchen.databinding.FragmentMapFoodRecipeBinding
import com.john.magickitchen.domain.models.LocationFoodRecipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFoodRecipeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var locationFoodRecipe: LocationFoodRecipe
    private var _binding: FragmentMapFoodRecipeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapFoodRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationFoodRecipe = arguments?.get(locationRecipe) as LocationFoodRecipe
        getMapAsync()
    }

    private fun getMapAsync() {
        val mapFragment =
            childFragmentManager.findFragmentById(binding.mapFragment.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        addMarkerToMap(googleMap)
    }

    private fun addMarkerToMap(googleMap: GoogleMap) {
        val location = LatLng(locationFoodRecipe.latitude, locationFoodRecipe.longitude)
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title(locationFoodRecipe.name)
                .icon(getIconFoodRecipe())
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    private fun getIconFoodRecipe(): BitmapDescriptor {
        val color = ContextCompat.getColor(requireContext(), R.color.yellow_700)
        return BitmapHelper.vectorToBitmap(
            requireContext(),
            R.drawable.ic_baseline_fastfood_24,
            color
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}