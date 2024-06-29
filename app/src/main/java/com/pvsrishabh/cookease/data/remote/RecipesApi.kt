package com.pvsrishabh.cookease.data.remote

import com.pvsrishabh.cookease.data.remote.dto.RecipesResponse
import com.pvsrishabh.cookease.data.remote.dto.SearchResponse
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {

    @GET("random")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String = API_KEY
    ): RecipesResponse

    @GET("complexSearch")
    suspend fun searchRecipes(
        @Query("q") searchQuery: String,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("addRecipeInformation") addRecipeInformation: String = "true",
        @Query("fillIngredients") fillIngredients: String = "true"
    ): SearchResponse
}