package com.pvsrishabh.cookease.domain.usecases.recipes

import androidx.paging.PagingData
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class SearchRecipes(
    private val recipesRepository: RecipesRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<Recipe>> {
        return recipesRepository.searchRecipes(searchQuery = searchQuery)
    }
}