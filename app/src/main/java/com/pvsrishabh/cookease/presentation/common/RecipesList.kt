package com.pvsrishabh.cookease.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.presentation.Dimens.ExtraSmallPadding2
import com.pvsrishabh.cookease.presentation.Dimens.MediumPadding1
import com.pvsrishabh.cookease.util.Constants.ADUNITID

@Composable
fun RecipesList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onClick: (Recipe) -> Unit
) {
    if(recipes.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(count = recipes.size) {
            val recipe = recipes[it]
            RecipeCard(recipe = recipe, onClick = { onClick(recipe) })
        }
    }
}

fun LazyListScope.RecipesList(
    recipes: LazyPagingItems<Recipe>,
    onClick: (Recipe) -> Unit
) {
    items(count = recipes.itemCount) {
        val handlePagingResult = handlePagingResult(recipes = recipes)
        if (handlePagingResult) {
            recipes[it]?.let {
                RecipeCard(recipe = it, onClick = { onClick(it) })
            }
            if ((it + 1) % 5 == 0) {
                AdMobBanner(
                    adUnitId = ADUNITID,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
        }
    }
}

@Composable
fun HorizontalRecipesList(
    modifier: Modifier = Modifier,
    recipes: LazyPagingItems<Recipe>,
    onClick: (Recipe) -> Unit
) {
    val handlePagingResult = handlePagingResult(recipes = recipes)
    if (handlePagingResult) {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
        ) {
            items(count = recipes.itemCount) {
                recipes[it]?.let {
                    BoxRecipeCard(recipe = it, onClick = { onClick(it) })
                }
            }
        }
    }
}

@Composable
fun SearchRecipesList(
    modifier: Modifier = Modifier,
    recipes: LazyPagingItems<Recipe>,
    onClick: (Recipe) -> Unit
) {
    val handlePagingResult = handlePagingResult(recipes = recipes)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            items(count = recipes.itemCount) { it ->
                recipes[it]?.let {recipe1 ->
                    SearchRecipeCard(recipe = recipe1,onClick = { onClick(recipe1) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    recipes: LazyPagingItems<Recipe>
): Boolean {
    val loadState = recipes.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(
                error = error
            )
            false
        }

        recipes.itemCount == 0 -> {
            EmptyScreen()
            false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            RecipeCardShimmerEffect(
                modifier = Modifier.padding(start = MediumPadding1)
            )
        }
    }
}