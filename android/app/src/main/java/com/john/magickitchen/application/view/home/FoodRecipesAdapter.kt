package com.john.magickitchen.application.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.john.magickitchen.databinding.ItemFoodRecipeBinding
import com.john.magickitchen.domain.models.FoodRecipe

class FoodRecipesAdapter :
    ListAdapter<FoodRecipe, FoodRecipesViewHolder>(FoodRecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecipesViewHolder {
        return FoodRecipesViewHolder(
            ItemFoodRecipeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodRecipesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class FoodRecipeDiffCallback : DiffUtil.ItemCallback<FoodRecipe>() {
    override fun areItemsTheSame(
        oldItem: FoodRecipe,
        newItem: FoodRecipe
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: FoodRecipe,
        newItem: FoodRecipe
    ): Boolean =
        oldItem == newItem
}