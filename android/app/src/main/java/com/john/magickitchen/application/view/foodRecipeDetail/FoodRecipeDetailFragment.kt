package com.john.magickitchen.application.view.foodRecipeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.john.magickitchen.R
import com.john.magickitchen.application.view.home.FoodRecipesViewHolder.Companion.idFoodRecipe
import com.john.magickitchen.application.view.shared.setImageOfFoodRecipe
import com.john.magickitchen.application.viewmodels.foodRecipeDetail.FoodRecipeDetailViewModel
import com.john.magickitchen.application.viewmodels.foodRecipeDetail.StatusFoodRecipeDetail
import com.john.magickitchen.databinding.FragmentFoodRecipeDetailBinding
import com.john.magickitchen.domain.models.FoodRecipeDetail
import com.john.magickitchen.domain.models.LocationFoodRecipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodRecipeDetailFragment : Fragment() {

    private val viewModel: FoodRecipeDetailViewModel by viewModels()
    private var _binding: FragmentFoodRecipeDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFoodRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi() {
        val idFoodRecipe = arguments?.getInt(idFoodRecipe) ?: notFoundIdFoodRecipe
        viewModel.getFoodRecipeById(idFoodRecipe).observe(viewLifecycleOwner) { status ->
            validateStatusFoodRecipeDetail(status)
        }
    }

    private fun validateStatusFoodRecipeDetail(status: StatusFoodRecipeDetail) {
        when (status) {
            is StatusFoodRecipeDetail.ShowLoader -> {
                showLoader(status.isLoading)
            }
            is StatusFoodRecipeDetail.ShowFoodRecipeDetail -> {
                showFoodRecipeDetail(status.foodRecipeDetail)
            }
            is StatusFoodRecipeDetail.ShowError -> {
                showError()
            }
        }
    }

    private fun showLoader(isLoading: Boolean) {
        binding.loaderFoodRecipesDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showFoodRecipeDetail(foodRecipeDetail: FoodRecipeDetail) {
        binding.ivPosterRecipe.setImageOfFoodRecipe(foodRecipeDetail.image)
        binding.tvName.text = foodRecipeDetail.name
        binding.tvPreparationTime.text = foodRecipeDetail.preparationTime
        binding.tvCategory.text = foodRecipeDetail.category
        binding.tvIngredients.text = foodRecipeDetail.ingredients
        binding.tvDescription.text = foodRecipeDetail.description
        setOnClickSeeLocationRecipe(foodRecipeDetail.location)
        binding.notFountData.visibility = View.GONE
        binding.ctlRecipeFoodDetail.visibility = View.VISIBLE
    }

    private fun setOnClickSeeLocationRecipe(location: LocationFoodRecipe) {
        binding.btnLocationRecipe.setOnClickListener {
            findNavController().navigate(
                R.id.action_FoodRecipeDetailFragment_to_MapFoodRecipeFragment, bundleOf(
                    locationRecipe to location
                )
            )
        }
    }

    private fun showError() {
        binding.ctlRecipeFoodDetail.visibility = View.GONE
        binding.notFountData.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val notFoundIdFoodRecipe = -1
        const val locationRecipe = "locationRecipe"
    }
}