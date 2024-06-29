package com.pvsrishabh.cookease.presentation.route

sealed class Route(
    val route: String
){
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")
    object FavouriteScreen : Route(route = "favouriteScreen")
    object DetailsScreen : Route(route = "detailsScreen")

    object SignInScreen : Route(route = "signInScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")
    object CookEaseNavigation : Route(route = "formNavigation")

    object RecipeInfo1 : Route(route = "recipeInfo1")
    object RecipeInfo : Route(route = "recipeInfo")

}