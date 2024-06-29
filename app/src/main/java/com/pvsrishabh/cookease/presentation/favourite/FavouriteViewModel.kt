package com.pvsrishabh.cookease.presentation.favourite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pvsrishabh.cookease.domain.usecases.recipes.RecipesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val recipesUseCases: RecipesUseCases
): ViewModel() {

    private val _state = mutableStateOf(FavouriteState())
    val state: State<FavouriteState> = _state

    init {
        getRecipes()
    }

    private fun getRecipes(){
        recipesUseCases.selectRecipes().onEach {
            _state.value = _state.value.copy(recipes = it.asReversed())
        }.launchIn(viewModelScope)
    }
}