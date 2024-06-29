package com.pvsrishabh.cookease.domain.usecases.recipes

data class RecipesUseCases(
    val getRecipes: GetRecipes,
    val searchRecipes: SearchRecipes,
    val upsertRecipe: UpsertRecipe,
    val deleteRecipe: DeleteRecipe,
    val selectRecipes: SelectRecipes,
    val selectRecipe: SelectRecipe
)