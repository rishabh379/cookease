package com.pvsrishabh.cookease.data.remote.dto

import com.pvsrishabh.cookease.domain.model.Recipe

data class RecipesResponse(
    val recipes: List<Recipe>,
)