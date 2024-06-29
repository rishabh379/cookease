package com.pvsrishabh.cookease.presentation.recipe_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pvsrishabh.cookease.R
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.presentation.Dimens
import com.pvsrishabh.cookease.presentation.common.CustomCard
import com.pvsrishabh.cookease.presentation.common.IngredientsList
import com.pvsrishabh.cookease.presentation.route.Route

@Composable
fun RecipeDetailScreen(
    recipe: Recipe
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        TopBar(title = recipe.title)

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.RecipeInfo1.route
        ) {
            composable(
                route = Route.RecipeInfo1.route
            ) {
                RecipeInfo1(recipe = recipe, onClick = {
                    navController.navigate(Route.RecipeInfo.route)
                }
                )
            }
            composable(
                route = Route.RecipeInfo.route
            ) {
                RecipeInfo(recipe = recipe)
            }
        }
    }
}

@Composable
fun RecipeInfo1(
    recipe: Recipe,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context).data(recipe.image).build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomCard(text = "Ready in", textValue = recipe.readyInMinutes.toString())
            CustomCard(text = "Servings", textValue = recipe.servings.toString())
            CustomCard(
                text = "Price/Serving",
                textValue = recipe.pricePerServing.toString()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                "Get Full Recipe",
                color = Color.White
            )
        }
    }
}

@Composable
fun RecipeInfo(
    recipe: Recipe
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = Dimens.MediumPadding1,
            end = Dimens.MediumPadding1
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "Ingredients",
                modifier = Modifier.padding(bottom = 10.dp),
                style = MaterialTheme.typography.displaySmall,
                color = colorResource(
                    id = R.color.text_title
                )
            )
        }
        IngredientsList(ingredients = recipe.extendedIngredients)
        item {
            FullRecipeContent(recipe = recipe)

            Button(
                // TODO("Similar Recipes")
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange)),
            ) {
                Text(
                    "Get Similar Recipes",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TopBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle back navigation */ }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = { /* Handle favorite */ }) {
            Icon(Icons.Default.Favorite, contentDescription = "Favorite")
        }
    }
}


@Composable
fun FullRecipeContent(recipe: Recipe) {
    Column(
        modifier = Modifier.fillMaxWidth(),

        ) {
        Text(
            text = "Instructions",
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.displaySmall,
            color = colorResource(
                id = R.color.text_title
            ),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = recipe.instructions ?: stringResource(id = R.string.sample_instructions),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(
                id = R.color.body
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Summary",
            style = MaterialTheme.typography.displaySmall,
            color = colorResource(
                id = R.color.text_title
            ),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = recipe.summary ?: stringResource(id = R.string.sample_summary),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(
                id = R.color.body
            )
        )
        Text(
            text = "Health Status",
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.displaySmall,
            color = colorResource(
                id = R.color.text_title
            )
        )
        Text(
            text = if (recipe.veryHealthy != false) "Healthy" else "Unhealthy",
            modifier = Modifier.padding(vertical = 10.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(
                id = R.color.body
            )
        )
    }
}