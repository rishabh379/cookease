package com.pvsrishabh.cookease.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pvsrishabh.cookease.domain.model.Recipe

class RecipesPagingSource(
    private val recipesApi: RecipesApi
) : PagingSource<Int, Recipe>() {

    private var totalRecipesCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val page = params.key ?: 1
        return try {
            val recipesResponse = recipesApi.getRecipes()
            val recipe =
                recipesResponse.recipes.distinctBy { it.id } // remove duplicates by same titles
            LoadResult.Page(
                data = recipe,
                nextKey = if (totalRecipesCount == recipesResponse.recipes.size) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}