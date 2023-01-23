package com.john.magickitchen.application.view.shared

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

private const val widthOfImage = 600
private const val heightOfImage = 400
private const val roundingRadiusImage = 20

fun AppCompatImageView.setImageOfFoodRecipe(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .override(widthOfImage, heightOfImage)
        .transform(RoundedCorners(roundingRadiusImage))
        .into(this)
}


