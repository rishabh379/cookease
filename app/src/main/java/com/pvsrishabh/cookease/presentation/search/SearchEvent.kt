package com.pvsrishabh.cookease.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()
    object SearchRecipes : SearchEvent()
}