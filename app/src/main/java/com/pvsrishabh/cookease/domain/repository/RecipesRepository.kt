package com.pvsrishabh.cookease.domain.repository

import androidx.paging.PagingData
import com.pvsrishabh.cookease.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(): Flow<PagingData<Recipe>>
    fun searchRecipes(searchQuery: String): Flow<PagingData<Recipe>>
    suspend fun upsertRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    fun selectRecipes(): Flow<List<Recipe>>
    suspend fun selectRecipe(id: Int): Recipe?
}