package com.pvsrishabh.cookease.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pvsrishabh.cookease.presentation.details.DetailsEvent
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.domain.usecases.recipes.RecipesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val recipesUseCases: RecipesUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteRecipe -> {
                viewModelScope.launch{
                    val article = recipesUseCases.selectRecipe(event.recipe.id)
                    if(article == null){
                        upsertArticle(event.recipe)
                    }else{
                        deleteArticle(event.recipe)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(recipe: Recipe) {
        recipesUseCases.deleteRecipe(recipe = recipe)
        sideEffect = "Recipe Deleted"
    }

    private suspend fun upsertArticle(recipe: Recipe) {
        recipesUseCases.upsertRecipe(recipe = recipe)
        sideEffect = "Recipe Saved"
    }
}