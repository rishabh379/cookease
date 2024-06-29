package com.pvsrishabh.cookease.presentation.search

import androidx.paging.PagingData
import com.pvsrishabh.cookease.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val recipes: Flow<PagingData<Recipe>>? = null,
)