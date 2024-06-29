package com.pvsrishabh.cookease.domain.usecases.recipes

import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class SelectRecipes(
    private val recipesRepository: RecipesRepository
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return recipesRepository.selectRecipes()
    }
}