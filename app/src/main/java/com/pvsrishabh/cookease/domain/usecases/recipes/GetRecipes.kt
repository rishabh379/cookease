package com.pvsrishabh.cookease.domain.usecases.recipes

import androidx.paging.PagingData
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class GetRecipes(
    private val recipesRepository: RecipesRepository
) {
    operator fun invoke(): Flow<PagingData<Recipe>> {
        return recipesRepository.getRecipes()
    }
}