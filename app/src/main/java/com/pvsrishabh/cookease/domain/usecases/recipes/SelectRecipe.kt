package com.pvsrishabh.cookease.domain.usecases.recipes

import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.domain.repository.RecipesRepository

class SelectRecipe (
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(id: Int): Recipe?{
        return recipesRepository.selectRecipe(id)
    }
}