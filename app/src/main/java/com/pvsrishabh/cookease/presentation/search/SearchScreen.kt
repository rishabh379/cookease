package com.pvsrishabh.cookease.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.presentation.Dimens.MediumPadding1
import com.pvsrishabh.cookease.presentation.common.SearchBar
import com.pvsrishabh.cookease.presentation.common.SearchRecipesList
import com.pvsrishabh.cookease.presentation.recipe_details.RecipeDetailScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit
) {
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(
                    top = MediumPadding1,
                    start = MediumPadding1,
                    end = MediumPadding1
                )
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            SearchBar(
                text = state.searchQuery,
                readOnly = false,
                onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
                onSearch = { event(SearchEvent.SearchRecipes) }
            )

            Spacer(modifier = Modifier.height(MediumPadding1))

            state.recipes?.let {
                val recipes = it.collectAsLazyPagingItems()
                SearchRecipesList(
                    recipes = recipes,
                    onClick = { recipe ->
                        selectedRecipe = recipe
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    }
                )
            }
        }

        // Bottom Sheet
        if (selectedRecipe != null) {
            ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = {
                    selectedRecipe = null
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                ) {
                    RecipeDetailScreen(recipe = selectedRecipe!!)
                }
            }
        }
    }

    LaunchedEffect(selectedRecipe) {
        if (selectedRecipe != null) {
            coroutineScope.launch {
                bottomSheetState.show()
            }
        }
    }
}
