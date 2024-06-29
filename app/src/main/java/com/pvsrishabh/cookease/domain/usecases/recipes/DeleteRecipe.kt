package com.pvsrishabh.cookease.domain.usecases.recipes

import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.domain.repository.RecipesRepository

class DeleteRecipe (
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(recipe: Recipe){
        recipesRepository.deleteRecipe(recipe)
    }
}