package com.pvsrishabh.cookease.presentation.recipes_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.pvsrishabh.cookease.R
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.presentation.details.DetailsEvent
import com.pvsrishabh.cookease.presentation.details.DetailsScreen
import com.pvsrishabh.cookease.presentation.details.DetailsViewModel
import com.pvsrishabh.cookease.presentation.favourite.FavouriteScreen
import com.pvsrishabh.cookease.presentation.favourite.FavouriteViewModel
import com.pvsrishabh.cookease.presentation.home.HomeScreen
import com.pvsrishabh.cookease.presentation.home.HomeViewModel
import com.pvsrishabh.cookease.presentation.route.Route
import com.pvsrishabh.cookease.presentation.recipes_navigator.components.BottomNavigationItem
import com.pvsrishabh.cookease.presentation.recipes_navigator.components.RecipesBottomNavigation
import com.pvsrishabh.cookease.presentation.search.SearchScreen
import com.pvsrishabh.cookease.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
            BottomNavigationItem(icon = R.drawable.ic_favourite, "Favourite")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.FavouriteScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {

        backStackState?.destination?.route == Route.HomeScreen.route ||
                  backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.FavouriteScreen.route

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                RecipesBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.HomeScreen.route)
                            1 -> navigateToTab(navController, Route.FavouriteScreen.route)
                        }
                    })
            }
        }
    ) { it ->
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val recipes = viewModel.recipes.collectAsLazyPagingItems()
                HomeScreen(
                    recipes = recipes,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { recipe ->
                        navigateToDetails(
                            navController = navController,
                            recipe = recipe
                        )
                    })
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if(viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Recipe>("recipe")
                    ?.let { recipe ->
                        DetailsScreen(recipe = recipe, event = viewModel::onEvent, navigateUp = {
                            navController.navigateUp()
                        })
                    }
            }

            composable(route = Route.FavouriteScreen.route) {
                val viewModel: FavouriteViewModel = hiltViewModel()
                val state = viewModel.state.value
                FavouriteScreen(
                    state = state,
                    navigateToDetails = { recipe ->
                        navigateToDetails(
                            navController = navController,
                            recipe = recipe
                        )
                    })
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }

}

private fun navigateToDetails(navController: NavController, recipe: Recipe) {
    navController.currentBackStackEntry?.savedStateHandle?.set("recipe", recipe)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}