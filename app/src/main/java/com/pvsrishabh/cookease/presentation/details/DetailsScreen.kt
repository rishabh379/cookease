package com.pvsrishabh.cookease.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pvsrishabh.cookease.R
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.presentation.Dimens.MediumPadding1
import com.pvsrishabh.cookease.presentation.common.CustomCard
import com.pvsrishabh.cookease.presentation.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    recipe: Recipe,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(recipe.spoonacularSourceUrl)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, recipe.spoonacularSourceUrl)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteRecipe(recipe = recipe)) },
            onBackClick = navigateUp
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = MediumPadding1,
                    end = MediumPadding1,
                    top = MediumPadding1
                )
            ) {
                item {
                    Box(
                        contentAlignment = Alignment.BottomStart
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context).data(recipe.image).build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = recipe.title,
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.displaySmall,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(MediumPadding1))
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
                    Spacer(modifier = Modifier.height(MediumPadding1))
                    Text(
                        text = "Instructions",
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                        style = MaterialTheme.typography.displaySmall,
                        color = colorResource(
                            id = R.color.text_title
                        ),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(MediumPadding1))
                    Text(
                        text = recipe.instructions ?: stringResource(id = R.string.sample_instructions),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.body
                        )
                    )
                    Spacer(modifier = Modifier.height(MediumPadding1))
                    Text(
                        text = "Summary",
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                        style = MaterialTheme.typography.displaySmall,
                        color = colorResource(
                            id = R.color.text_title
                        ),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(MediumPadding1))
                    Text(
                        text = recipe.summary ?: stringResource(id = R.string.sample_summary),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.body
                        )
                    )
                    Spacer(modifier = Modifier.height(MediumPadding1))
                    Text(
                        text = "Health Status",
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                        style = MaterialTheme.typography.displaySmall,
                        color = colorResource(
                            id = R.color.text_title
                        )
                    )
                    Spacer(modifier = Modifier.height(MediumPadding1))
                    Text(
                        text = if (recipe.veryHealthy != false) "Healthy" else "Unhealthy",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.body
                        )
                    )
                }
            }
        }
    }
}