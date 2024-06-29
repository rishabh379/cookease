package com.pvsrishabh.cookease.domain.usecases.recipes

import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.domain.repository.RecipesRepository

class UpsertRecipe(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(recipe: Recipe){
        recipesRepository.upsertRecipe(recipe)
    }
}