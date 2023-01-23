package com.john.magickitchen.application.view.home

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.john.magickitchen.R
import com.john.magickitchen.application.view.shared.setImageOfFoodRecipe
import com.john.magickitchen.databinding.ItemFoodRecipeBinding
import com.john.magickitchen.domain.models.FoodRecipe

class FoodRecipesViewHolder(
    private val itemFoodRecipeBinding: ItemFoodRecipeBinding
) : RecyclerView.ViewHolder(itemFoodRecipeBinding.root) {

    fun bind(item: FoodRecipe) {
        itemFoodRecipeBinding.tvTitleFoodRecipe.text = item.name
        itemFoodRecipeBinding.imbFoodRecipe.setImageOfFoodRecipe(item.image)
        onClickItemFoodRecipe(item.id)
    }

    private fun onClickItemFoodRecipe(id: Int) {
        with(itemFoodRecipeBinding.root) {
            setOnClickListener {
                findNavController().navigate(
                    R.id.action_HomeFragment_to_FoodRecipeDetailFragment, bundleOf(
                        idFoodRecipe to id
                    )
                )
            }
        }
    }

    companion object {
        const val idFoodRecipe = "id"
    }
}