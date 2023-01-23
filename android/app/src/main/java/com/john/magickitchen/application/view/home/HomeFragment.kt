package com.john.magickitchen.application.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.john.magickitchen.application.viewmodels.home.HomeViewModel
import com.john.magickitchen.application.viewmodels.home.StatusHomeView
import com.john.magickitchen.databinding.FragmentHomeBinding
import com.john.magickitchen.domain.models.FoodRecipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        subscribeOnSearchRecipeFood()
    }

    private fun subscribeUi() {
        viewModel.getFoodRecipes().observe(viewLifecycleOwner) { status ->
            validateStatusHomeView(status)
        }
        binding.rvFoodRecipes.adapter = FoodRecipesAdapter()
    }

    private fun subscribeOnSearchRecipeFood() {
        binding.searchFoodRecipes.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchFoodRecipes(newText).observe(viewLifecycleOwner) { status ->
                    validateStatusHomeView(status)
                }
                return false
            }
        })
    }

    private fun validateStatusHomeView(status: StatusHomeView) {
        when (status) {
            is StatusHomeView.ShowLoader -> {
                showLoader(status.isLoading)
            }
            is StatusHomeView.ShowFoodRecipes -> {
                showFoodRecipes(status.foodRecipes)
            }
            is StatusHomeView.ShowError -> {
                showError()
            }
        }
    }

    private fun showLoader(isLoading: Boolean) {
        binding.loaderFoodRecipes.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showFoodRecipes(foodRecipes: List<FoodRecipe>) {
        (binding.rvFoodRecipes.adapter as FoodRecipesAdapter).submitList(foodRecipes)
        binding.notFountData.visibility = View.GONE
        binding.rvFoodRecipes.visibility = View.VISIBLE
    }

    private fun showError() {
        binding.rvFoodRecipes.visibility = View.GONE
        binding.notFountData.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}