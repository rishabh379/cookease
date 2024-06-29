package com.pvsrishabh.cookease.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Recipe(
    val extendedIngredients: List<ExtendedIngredient>,
    @PrimaryKey val id: Int,
    val image: String,
    val instructions: String? = "",
    val pricePerServing: Double? = 0.0,
    val readyInMinutes: Int? = 0,
    val servings: Int? = 0,
    val spoonacularSourceUrl: String? = "",
    val summary: String? = "",
    val title: String,
    val veryHealthy: Boolean? = false
): Parcelable