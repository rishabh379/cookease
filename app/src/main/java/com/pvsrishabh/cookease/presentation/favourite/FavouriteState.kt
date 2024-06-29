package com.pvsrishabh.cookease.presentation.favourite

import com.pvsrishabh.cookease.domain.model.Recipe

data class FavouriteState(
    val recipes: List<Recipe> = emptyList()
)
