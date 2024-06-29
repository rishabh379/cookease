package com.pvsrishabh.cookease.presentation.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.pvsrishabh.cookease.R
import com.pvsrishabh.cookease.domain.model.Recipe
import com.pvsrishabh.cookease.presentation.Dimens.MediumPadding1
import com.pvsrishabh.cookease.presentation.common.RecipesList

@Composable
fun FavouriteScreen(
    state: FavouriteState,
    navigateToDetails: (Recipe) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
        ) {
            Text(
                text = "Favourite",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.text_title)
            )

            Spacer(modifier = Modifier.height(MediumPadding1))

        }
        RecipesList(recipes = state.recipes, onClick = { navigateToDetails(it) })
    }
}