package com.pvsrishabh.cookease.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.pvsrishabh.cookease.R
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.presentation.Dimens
import com.pvsrishabh.cookease.presentation.Dimens.MediumPadding1
import com.pvsrishabh.cookease.presentation.Dimens.SmallPaddingSize
import com.pvsrishabh.cookease.presentation.common.AdMobBanner
import com.pvsrishabh.cookease.presentation.common.HorizontalRecipesList
import com.pvsrishabh.cookease.presentation.common.RecipeCard
import com.pvsrishabh.cookease.presentation.common.RecipesList
import com.pvsrishabh.cookease.presentation.common.SearchBar
import com.pvsrishabh.cookease.presentation.common.handlePagingResult
import com.pvsrishabh.cookease.util.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    recipes: LazyPagingItems<Recipe>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Recipe) -> Unit
) {
    val userName = Firebase.auth.currentUser?.displayName
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        item {
            Text(
                text = "👋 Hey ${userName}",
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
                fontSize = 24.sp,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.display_small),
            )
            Text(
                text = "Discover tasty and healthy recipe",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MediumPadding1),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            SearchBar(
                modifier = Modifier.padding(horizontal = MediumPadding1),
                text = "",
                readOnly = true,
                onValueChange = {},
                onClick = {
                    navigateToSearch()
                },
                onSearch = {}
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                text = "Popular Recipes",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MediumPadding1),
                fontSize = 16.sp,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.display_small)
            )
            Spacer(modifier = Modifier.height(SmallPaddingSize))
        }
        item {
            HorizontalRecipesList(
                modifier = Modifier.padding(horizontal = MediumPadding1),
                recipes = recipes,
                onClick = {
                    navigateToDetails(it)
                })
        }
        item {
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                text = "All Recipes",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MediumPadding1),
                fontSize = 16.sp,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.display_small)
            )
            Spacer(modifier = Modifier.height(SmallPaddingSize))
        }
        RecipesList(
            recipes = recipes,
            onClick = {
                navigateToDetails(it)
            })
    }
}