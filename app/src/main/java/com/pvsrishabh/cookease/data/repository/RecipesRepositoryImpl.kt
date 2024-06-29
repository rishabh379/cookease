package com.pvsrishabh.cookease.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pvsrishabh.cookease.domain.repository.RecipesRepository
import com.pvsrishabh.cookease.data.local.RecipesDao
import com.pvsrishabh.cookease.data.remote.RecipesApi
import com.pvsrishabh.cookease.data.remote.RecipesPagingSource
import com.pvsrishabh.cookease.data.remote.SearchRecipesPagingSource
import com.pvsrishabh.cookease.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

class RecipesRepositoryImpl(
    private val recipesApi: RecipesApi,
    private val recipesDao: RecipesDao
): RecipesRepository {
    override fun getRecipes(): Flow<PagingData<Recipe>> {
        return  Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                RecipesPagingSource(
                    recipesApi = recipesApi
                )
            }
        ).flow
    }

    override fun searchRecipes(searchQuery: String): Flow<PagingData<Recipe>> {
        return  Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchRecipesPagingSource(
                    searchQuery = searchQuery,
                    recipesApi = recipesApi
                )
            }
        ).flow
    }

    override suspend fun upsertRecipe(recipe: Recipe) {
        recipesDao.upsert(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipesDao.delete(recipe)
    }

    override fun selectRecipes(): Flow<List<Recipe>> {
        return recipesDao.getRecipes()
    }

    override suspend fun selectRecipe(id: Int): Recipe? {
        return recipesDao.getRecipe(id = id)
    }

}