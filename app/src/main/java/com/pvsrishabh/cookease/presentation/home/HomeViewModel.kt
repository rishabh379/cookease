package com.pvsrishabh.cookease.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pvsrishabh.cookease.domain.usecases.recipes.RecipesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    recipesUseCases: RecipesUseCases
): ViewModel() {
    val recipes = recipesUseCases.getRecipes().cachedIn(viewModelScope)
}