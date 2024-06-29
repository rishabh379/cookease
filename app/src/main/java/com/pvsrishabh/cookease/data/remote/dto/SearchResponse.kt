package com.pvsrishabh.cookease.data.remote.dto

import com.pvsrishabh.cookease.domain.model.Recipe

data class SearchResponse(
    val number: Int,
    val offset: Int,
    val results: List<Recipe>,
    val totalResults: Int
)