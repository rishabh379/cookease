package com.pvsrishabh.cookease.presentation.details

import com.pvsrishabh.cookease.domain.model.Recipe

sealed class DetailsEvent {
    data class UpsertDeleteRecipe(val recipe: Recipe): DetailsEvent()
    object RemoveSideEffect: DetailsEvent()
}